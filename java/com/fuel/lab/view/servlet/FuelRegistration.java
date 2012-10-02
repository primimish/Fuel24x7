package com.fuel.lab.view.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fuel.common.persistence.jdbc.Persister;
import com.fuel.lab.regst.exception.FuelRegstrationException;

public class FuelRegistration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String filePath = "";
	private boolean isMultipart;
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 400 * 1024;
	private File file;
	private String toMailId = "";
	private String fileName = "";
	private String email = "";
	private String msg_from_DB = "";
	private String host = "";
	private String port = "";
	private String fromMailID = "";
	private String password = "";
	private String[] toMailID = null;
	private String tempPath = "";
	List<String> errorMap = new ArrayList<String>();

	/**
	 * Constructor of the object.
	 */

	public FuelRegistration() {
		super();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		ResourceBundle bundle = ResourceBundle.getBundle("essentials");

		tempPath = bundle.getString("temp");
		filePath = bundle.getString("filePath");
		host = bundle.getString("host");
		port = bundle.getString("port");
		fromMailID = bundle.getString("fromMailID");
		password = bundle.getString("password");
		toMailID = bundle.getString("toMailID").split(",");

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map map = new HashMap();

		PrintWriter out = response.getWriter();

		boolean uploadFile = false;

		// --------------------------------------------------------------------------------------------
		// Check that we have a file upload request

		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");

		if (!isMultipart) {

			errorMap.add("Please attach the Resume ");
			doGet(request, response);
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory

		factory.setSizeThreshold(maxMemSize);

		// Location to save data that is larger than maxMemSize.

		factory.setRepository(new File(tempPath));

		// Create a new file upload handler

		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.

		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items.

			List<FileItem> fileItems = upload.parseRequest(request);

			// -----------------------------------

			for (FileItem item : fileItems) {
				if (item.isFormField()) {

					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					map.put(fieldname, fieldvalue);
					System.out.println(fieldname + "," + fieldvalue);

				} else {

					uploadFile = uploadResumeToFlabs(item);

				}

			}

			// -------------------------------------
			String lastName = (String) map.get("lname");
			String gender = (String) map.get("Sex");
			email = (String) map.get("EmailId");
			String firstName = (String) map.get("fname");
			String middleName = (String) map.get("mName");
			String address1 = (String) map.get("Add1");
			String bloodGroup = (String) map.get("bloodGrop");
			String day = (String) map.get("day");
			String month = (String) map.get("month");
			String year = (String) map.get("year");

			String locality = (String) map.get("lId");
			String address2 = (String) map.get("Add2");
			String mobile = (String) map.get("mobNum");
			String zip = (String) map.get("PosID");
			String state = (String) map.get("sId");
			String city = (String) map.get("cId");

			String dob = month + "/" + day + "/" + year;

			System.out.println("values---------------" + firstName + lastName
					+ address1 + dob + email + gender + middleName + locality
					+ city + state + mobile + address2);
			// --------------------------------------------------------------------------------------------
			toMailId = email;
			// --------------------------------------------------------------------------------------------
			if (uploadFile && !checkRegisteredUser(email)) {
				msg_from_DB = addFuelCandidate(firstName, middleName, lastName,
						gender, email, dob, bloodGroup, mobile, address1,
						address2, locality, state, city, zip);

				if (msg_from_DB.equals("SUCCESS")) {

					boolean mailSuccess = sendMail(toMailId, filePath, fileName);

					if (mailSuccess) {

						out.println("<P><center><h3>Hello '" + firstName
								+ "' Welcome to Fuel-labs  </h3></center><br>");

						RequestDispatcher rd = getServletContext()
								.getRequestDispatcher(
										"/WEB-INF/jsp/success.jsp");

						rd.forward(request, response);

					}
				}
			} else {

				doGet(request, response);
				deleteResumeFromFlabs(filePath + fileName);
			}

			// --------------------------------------------------------------------------------------------
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();

			doGet(request, response);
		}

		// --------------------------------------------------------------------------------------------
		// out.flush();
		// out.close();

	}

	private boolean uploadResumeToFlabs(FileItem item) throws Exception {

		try {

			String fieldName = item.getFieldName();

			System.out.println("-----fieldName------" + fieldName);

			fileName = item.getName();

			System.out.println("-----fielName------" + fileName);

			String contentType = item.getContentType();

			System.out.println("-----conttyp------" + contentType);

			// boolean isInMemory = item.isInMemory();
			// long sizeInBytes = item.getSize();
			// Write the file

			if (fileName.lastIndexOf("\\") >= 0) {
				file = new File(filePath
						+ fileName.substring(fileName.lastIndexOf("\\")));
			} else {
				file = new File(filePath
						+ fileName.substring(fileName.lastIndexOf("\\") + 1));
			}

			item.write(file);
			return true;
		} catch (Exception e) {
			String msg = "File Upload Failed....";
			errorMap.add(msg);
			e.printStackTrace();

			// doGet(request, response);
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		System.out.println("ERRORS---------------" + errorMap.toString());
		request.setAttribute("errors", errorMap);
		errorMap.clear();
		System.out.println("ERRORS---------------" + errorMap.toString());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/WEB-INF/jsp/error.jsp");

		rd.forward(request, response);

	}

	// --------------------------------------------------------------------------------------------
	// *************Java Mail API***********//
	// --------------------------------------------------------------------------------------------

	private boolean sendMail(String toMailId, String fileAttachment,
			String fileName) throws FuelRegstrationException {

		for (int i = 0; i < toMailID.length; i++) {

			System.out.println("-----MailIDS------" + toMailID[i]);
		}

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);

		// Get session
		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromMailID, password);
					}
				});
		try {

			// Define message
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(fromMailID));

			for (int i = 0; i < toMailID.length; i++) {

				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(toMailID[i]));
			}

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toMailId));

			message.setSubject("Hello Fulers");

			// create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// fill message
			messageBodyPart.setText("Hello Fuel-World");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileAttachment + fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// Send the message
			// //Transport.send(message);

			// deleteResumeFromFlabs(fileAttachment + fileName);

			return true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block

			String msg = "Email address is not valied";
			errorMap.add(msg);
			e.printStackTrace();

			throw new FuelRegstrationException(msg);
		} catch (MessagingException e) {
			String msg = "Massage Content is not valied or Mail Server is Down";
			errorMap.add(msg);
			e.printStackTrace();
			throw new FuelRegstrationException(msg);
		}

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Saving candidate to DB**************//
	// ---------------------------------------------------------------------------------------------------------

	private String deleteResumeFromFlabs(String file_name)
			throws FuelRegstrationException {

		File file = new File(file_name);

		boolean success = file.delete();
		if (!success) {
			System.out.println("Deletion failed.");
			return "Deletion failed.";
		} else {
			System.out.println("File deleted.");
			return "Deletion Sucess";

		}
	}

	public String addFuelCandidate(String firstName, String middleName,
			String lastName, String gender, String email, String dob,
			String bloodGroup, String mobile, String address1, String address2,
			String locality, String state, String city, String zip)
			throws FuelRegstrationException {

		String dataUId = getPersonUid(firstName, lastName, "", dob, gender,
				bloodGroup, "IN", state, city, locality, zip, mobile, "", "",
				"", "", "");
		System.out.println("------------dataUId---------------" + dataUId);

		String response = setUserDetails(firstName, middleName, lastName,
				gender, email, dob, bloodGroup, mobile, address1, address2,
				"IN", state, city, zip, dataUId);
		System.out.println("---------response-----------------" + response);

		return response;

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Checking existing candidate to DB**************//
	// ---------------------------------------------------------------------------------------------------------

	public boolean checkRegisteredUser(String email)
			throws FuelRegstrationException {

		boolean flag = true;
		System.out.println("Email in Cheecking" + email);
		if (email != null && !(email.equals(""))
				&& !(email.equals("null") && email.length() >= 6)) {
			boolean result = getUserFromEmail(email);

			System.out.println("--result--" + result);
			if (result) {
				flag = false;
			} else {

				errorMap.add("Looks like Registered User");
				return flag;
			}

			return flag;
		} else {

			errorMap.add("Email ID is incorrect please  fill correct email-id ");
			return flag;
		}

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Getting JNDI LookUp from Persister**************//
	// ---------------------------------------------------------------------------------------------------------
	public static Persister getPersister() throws FuelRegstrationException {

		try {
			return new Persister("java:comp/env/jdbc/biz_fuelDB");//
		} catch (Exception e) {
			throw new FuelRegstrationException("DB Server Not Found");
		}
	}

	/*
	 * 
	 */
	public boolean getUserFromEmail(String email)
			throws FuelRegstrationException {
		Persister persister = FuelRegistration.getPersister();
		// String result = null;

		List<List<String>> queryResult = new ArrayList<List<String>>();
		try {
			String query = "select DPL_ID from dir_people where  DPL_EMAIL like '"
					+ email + "'";
			queryResult = persister.search(query);
			System.out.println("-----queryResult--------" + queryResult);
			if (queryResult.isEmpty()) {
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			errorMap.add("Server is Not Rechable,Please Try Again Later");
			return false;

		}

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Saving candidate to DB**************//
	// ---------------------------------------------------------------------------------------------------------

	public String setUserDetails(String firstName, String middleName,
			String lastName, String gender, String email, String dob,
			String bloodGroup, String mobile, String address1, String address2,
			String country, String state, String city, String zip,
			String dataUId) throws FuelRegstrationException {

		Persister persister = FuelRegistration.getPersister();
		try {

			String pAddrId = insertPeopleAddess(address1, address2, zip, state,
					country, city, "U", new Date().toString(), "", "P",
					persister);
			System.out.println("--------pAddrId-----------" + pAddrId);

			if (pAddrId != null) {
				String response = insertPeopleDetail(firstName, middleName,
						lastName, gender, dob, mobile, email, dataUId, pAddrId,
						persister);
				System.out.println("-----------response------------------"
						+ response);
				return response;
			} else {

				errorMap.add("Somthing Went wrong please try after some time");
				return "FAIL";

			}

		} catch (Exception e) {

			errorMap.add("Somthing Went wrong please try after some time");
			return "FAIL";
		}

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************inserting candidate to DB**************//
	// ---------------------------------------------------------------------------------------------------------

	public String insertPeopleDetail(String firstName, String middleName,
			String lastName, String gender, String dob, String mobile,
			String email, String dataUId, String pAddrId, Persister persister)
			throws FuelRegstrationException {
		StringBuffer peopleDetail = new StringBuffer();
		try {
			peopleDetail
					.append("insert into dir_people ( DPL_FIRST_NAME ,DPL_MIDDLE_NAME ,DPL_LAST_NAME ,DPL_GENDER ,DPL_DOB ,DPL_CELL_NO,");
			peopleDetail
					.append(" DPL_EMAIL , DPL_DATA_UID, DPL_MAILING_ADD_ID ) VALUES ('");
			peopleDetail.append(firstName);
			peopleDetail.append("','");
			peopleDetail.append(middleName);
			peopleDetail.append("','");
			peopleDetail.append(lastName);
			peopleDetail.append("','");
			peopleDetail.append(gender);
			if (dob != null && !("").equals(dob) && !("null").equals(dob)) {
				peopleDetail.append("',STR_TO_DATE('");
				peopleDetail.append(dob);
				peopleDetail.append("','%m/%d/%Y'), '");
			} else {
				peopleDetail.append("','");
				peopleDetail.append(dob);
				peopleDetail.append("','");
			}
			peopleDetail.append(mobile);
			peopleDetail.append("','");
			peopleDetail.append(email);
			peopleDetail.append("','");
			peopleDetail.append(dataUId);
			peopleDetail.append("','");
			peopleDetail.append(pAddrId);
			peopleDetail.append("')");
			persister.execute(peopleDetail.toString());
			return "SUCCESS";
		} catch (Exception e) {

			errorMap.add("Somthing Went wrong please try after some time");

			return "FAIL";
		}
	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Saving candidate's Address to DB**************//
	// ---------------------------------------------------------------------------------------------------------

	public String insertPeopleAddess(String line1, String line2, String pin,
			String state, String country, String city, String furnished,
			String updateOn, String Landmark, String addressType,
			Persister persister) throws FuelRegstrationException {

		StringBuffer peopleAddr = new StringBuffer();
		peopleAddr
				.append("insert into dir_people_address ( DPA_ADDRESS_LINE1 ,DPA_ADDRESS_LINE2 ,DPA_PIN ,DPA_STATE ");
		peopleAddr
				.append(",DPA_COUNTRY ,DPA_CITY ,DPA_FURNISHED_BY_TYPE ,DPA_UPDATED_ON ,DPA_LANDMARK ,DPA_ADDRESS_TYPE ) VALUES ('");
		peopleAddr.append(line1);
		peopleAddr.append("','");
		peopleAddr.append(line2);
		peopleAddr.append("','");
		peopleAddr.append(pin);
		peopleAddr.append("','");
		peopleAddr.append(state);
		peopleAddr.append("','");
		peopleAddr.append(country);
		peopleAddr.append("','");
		peopleAddr.append(city);
		peopleAddr.append("','");
		peopleAddr.append(furnished);
		peopleAddr.append("',");
		peopleAddr.append("now()");
		peopleAddr.append(",'");
		peopleAddr.append(Landmark);
		peopleAddr.append("','");
		peopleAddr.append(addressType);
		peopleAddr.append("')");

		List<List<String>> idInList;
		try {

			persister.execute(peopleAddr.toString());

			idInList = persister
					.search("select max(DPA_ID) from dir_people_address");
			return idInList.get(0).get(0);

		} catch (Exception e) {
			errorMap.add("Somthing Went wrong please try affter some time");

			return null;
		}

	}

	// ---------------------------------------------------------------------------------------------------------
	// **************Genrating Personal UID**************//
	// ---------------------------------------------------------------------------------------------------------

	public static String getPersonUid(String firstName, String lastName,
			String age, String dob, String gender, String bloodgroup,
			String country, String state, String city, String locality,
			String zip, String phone, String passport, String dlicence,
			String pan, String adhaar, String profession)
			throws FuelRegstrationException {

		try {
			StringBuffer dataUid = new StringBuffer();
			dataUid.append(firstName + "$");
			dataUid.append(lastName + "$");
			dataUid.append(age + "$");
			dataUid.append(dob + "$");
			dataUid.append(gender + "$");
			dataUid.append(bloodgroup + "$");
			dataUid.append(country + "$");
			dataUid.append(state + "$");
			dataUid.append(city + "$");
			dataUid.append(locality + "$");
			dataUid.append(zip + "$");
			dataUid.append(phone + "$");
			dataUid.append(passport + "$");
			dataUid.append(dlicence + "$");
			dataUid.append(pan + "$");
			dataUid.append(adhaar + "$");
			dataUid.append(profession);

			return dataUid.toString();
		} catch (Exception e) {

			String msg = "oops!Something Went Wrong! please try after some time";
			throw new FuelRegstrationException(msg);
		}
	}
}
