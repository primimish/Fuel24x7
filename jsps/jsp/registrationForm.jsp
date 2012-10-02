<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registration Form</title>
<link href="http://www.fuel24x7labs.com/fuel.css" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css" />
<script src="http://www.fuel24x7labs.com/Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript" src="http://www.fuel24x7labs.com/js/validateForm.js"></script>
 
 <% String staticSever = "http://www.fuel24x7labs.com/";
 %>
 <style type="text/css">
 
            body {font-family:Arial, Sans-Serif;}
 
           /* #container {width:750px; height:110px; margin:0 auto;}  width:650px; */
 
            /* Nicely lines up the labels. */
           /* form label {display:inline-block; width:140px; color:#000000; font-family:Verdana, Arial, Helvetica, sans-serif;font-size:12px;font-weight:bold;color:#675c01;} */
 
            /* You could add a class to all the input boxes instead, if you like. That would be safer, and more backwards-compatible */
            form input[type="text"],
            form input[type="password"],
            form input[type="email"] {width:160px;}
 
            form .line {clear:both;}

            form .textArea {text-align:center;}
			form .line.submit {text-align:left;}


	 
 
        </style>
</head>




<body onload="document.myForm.fname.focus();">
<div id="wrapper" ><a href="registrationForm.jsp"><div class="anatomy-toplinksM"><strong>I am interested!</strong></div> </a> 
<div id="wrapper"><div class="toplink"></div> 
  <div class="mainContainer">
    <a href="<%=staticSever%>index.html"><div class="logo"></div></a>
    <div class="toptr-con">
      <div class="rtlogo"></div>
      <br />
      <a href="<%=staticSever%>Resources.html">
      <div class="topmenu selected">Resources</div>
        </a> <a href="<%=staticSever%>testimonials_1.html">
          <div class="topmenu">Fueler?</div>
          </a> <a href="<%=staticSever%>Fuel1.html">
      <div class="topmenu">Fuel?</div>
            </a> <a href="<%=staticSever%>partners.html">
              <div class="topmenu ">Partners?</div>
              </a> <a href="<%=staticSever%>Individual-partners.html">
                <div class="topmenu">Trainers, Coaches?</div>
                 </a> <a href="<%=staticSever%>meh_grooming.html">
                  <div class="topmenu">MEH  Grooming?</div>
                  </a> <a href="<%=staticSever%>home.html">
      <div class="topmenu">Why  MEH?</div>
                    </a> <br />
    </div>

<div class="topline"></div>
    <div class="inner-container">
      <div class="title1">Profile</div>
      <br />
      <br />


<div ><h2 class="form-header">Your profile</h2></div>   <!--class="form-header"  align="center" class="form-headerBackGround"    class="form-label4Name"-->
	 <div  class="form-backGround">  <!--id="container"  validateForm()-->
	  
     <form name="myForm" action="i-wish-2-meh.fuel" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
	 <table width="268px" border="1" align="left" cellpadding="0" cellspacing="0">
	 <tr width="266px">
         <td><label class="form-label4Name" for="fname" >First Name</label></td>
		 <td><input type="text"  value="" name="fname" id="fname" /></td>
		 <td><label class="form-label4Name" for="mName">Middle</label></td>
		 <td><input type="text" value="" name="mName" id="mName" style="width:96px" /></td>
		 <td><label class="form-label4Name" for="lname">Last</td>
		 <td><input type="text" value="" name="lname" id="lname" style="width:96px"/></td>
	 </tr>
	   <tr width="268px">
         <td  ><label class="form-label4Name" for="EmailId">Email</label></td>   
		 <td><input type="text"  value="" name="EmailId" id="EmailId" /></td> <!--email-->
		 <td ><label class="form-label4Name" for="mobNum">Mobile</label></td>
		 <td colspan=3><input type="text" value="" name="mobNum" id="mobNum" style="width:160px"/></td>
		 
	 </tr>

	  <tr width="268px">
         <td  ><label class="form-label4Name1" >Gender</label></td>
		 <td><input type="radio" value="M" name="Sex"  id="gen1"/>&nbsp; <span class="form-radioButton">Male  </span>  
<input type="radio" value="F" name="Sex" id="gen2" />&nbsp;<span class="form-radioButton">Female </span></td>
		 <td  ><label class="form-label4Name1" >DOB</label></td>
		 <td colspan=3><select class="select1" name="day" id="day" >  
<option label="" value="">--</option>

<option label="01" value="01" selected="selected">01</option>
<option label="02" value="02">02</option>
<option label="03" value="03">03</option>
<option label="04" value="04">04</option>
<option label="05" value="05">05</option>
<option label="06" value="06">06</option>
<option label="07" value="07">07</option>
<option label="08" value="08">08</option>
<option label="09" value="09">09</option>
<option label="10" value="10">10</option>
<option label="11" value="11">11</option>
<option label="12" value="12">12</option>
<option label="13" value="13">13</option>
<option label="14" value="14">14</option>
<option label="15" value="15">15</option>
<option label="16" value="16">16</option>
<option label="17" value="17">17</option>
<option label="18" value="18">18</option>
<option label="19" value="19">19</option>
<option label="20" value="20">20</option>
<option label="21" value="21">21</option>
<option label="22" value="22">22</option>
<option label="23" value="23">23</option>
<option label="24" value="24">24</option>
<option label="25" value="25">25</option>
<option label="26" value="26">26</option>
<option label="27" value="27">27</option>
<option label="28" value="28">28</option>
<option label="29" value="29">29</option>
<option label="30" value="30">30</option>
<option label="31" value="31">31</option>
</select>

 <select class="select1" name="month" id="month" > <!-- onChange="processFieldValueChange('month')"-->
<option label="" value="">--</option>

<option label="Jan" value="01" selected="selected">Jan</option>
<option label="Feb" value="02">Feb</option>
<option label="Mar" value="03">Mar</option>
<option label="Apr" value="04">Apr</option>
<option label="May" value="05">May</option>
<option label="Jun" value="06">Jun</option>
<option label="Jul" value="07">Jul</option>
<option label="Aug" value="08">Aug</option>
<option label="Sep" value="09">Sep</option>
<option label="Oct" value="10">Oct</option>
<option label="Nov" value="11">Nov</option>
<option label="Dec" value="12">Dec</option>
</select>

 <select class="select1" name="year" id="year" > <!-- onChange="processFieldValueChange('year')"-->
<option label="2012" value="2012" selected="selected">2012</option>
<option label="2011" value="2011">2011</option>
<option label="2010" value="2010">2010</option>
<option label="2009" value="2009">2009</option>
<option label="2008" value="2008">2008</option>
<option label="2007" value="2007">2007</option>
<option label="2006" value="2006">2006</option>
<option label="2005" value="2005">2005</option>
<option label="2004" value="2004">2004</option>
<option label="2003" value="2003">2003</option>
<option label="2002" value="2002">2002</option>
<option label="2001" value="2001">2001</option>
<option label="2000" value="2000">2000</option>
<option label="1999" value="1999">1999</option>

<option label="1998" value="1998">1998</option>
<option label="1997" value="1997">1997</option>
<option label="1996" value="1996">1996</option>
<option label="1995" value="1995">1995</option>
<option label="1994" value="1994">1994</option>
<option label="1993" value="1993">1993</option>
<option label="1992" value="1992">1992</option>
<option label="1991" value="1991">1991</option>
<option label="1990" value="1990">1990</option>
<option label="1989" value="1989">1989</option>
<option label="1988" value="1988">1988</option>
<option label="1987" value="1987">1987</option>
<option label="1986" value="1986">1986</option>
<option label="1985" value="1985">1985</option>
<option label="1984" value="1984">1984</option>
<option label="1983" value="1983">1983</option>
<option label="1982" value="1982">1982</option>
<option label="1981" value="1981">1981</option>
<option label="1980" value="1980">1980</option>
<option label="1979" value="1979">1979</option>
<option label="1978" value="1978">1978</option>
<option label="1977" value="1977">1977</option>
<option label="1976" value="1976">1976</option>
<option label="1975" value="1975">1975</option>
<option label="1974" value="1974">1974</option>
<option label="1973" value="1973">1973</option>
<option label="1972" value="1972">1972</option>
<option label="1971" value="1971">1971</option>
<option label="1970" value="1970">1970</option>
<option label="1969" value="1969">1969</option>
<option label="1968" value="1968">1968</option>
<option label="1967" value="1967">1967</option>
<option label="1966" value="1966">1966</option>
<option label="1965" value="1965">1965</option>
<option label="1964" value="1964">1964</option>
<option label="1963" value="1963">1963</option>
<option label="1962" value="1962">1962</option>
<option label="1961" value="1961">1961</option>
<option label="1960" value="1960">1960</option>
<option label="1959" value="1959">1959</option>
<option label="1958" value="1958">1958</option>
<option label="1957" value="1957">1957</option>
<option label="1956" value="1956">1956</option>
<option label="1955" value="1955">1955</option>
<option label="1954" value="1954">1954</option>
<option label="1953" value="1953">1953</option>
<option label="1952" value="1952">1952</option>
<option label="1951" value="1951">1951</option>
<option label="1950" value="1950">1950</option>
<option label="1949" value="1949">1949</option>
<option label="1948" value="1948">1948</option>
<option label="1947" value="1947">1947</option>
<option label="1946" value="1946">1946</option>
<option label="1945" value="1945">1945</option>
<option label="1944" value="1944">1944</option>
<option label="1943" value="1943">1943</option>
<option label="1942" value="1942">1942</option>
<option label="1941" value="1941">1941</option>
<option label="1940" value="1940">1940</option>
<option label="1939" value="1939">1939</option>
<option label="1938" value="1938">1938</option>
<option label="1937" value="1937">1937</option>
<option label="1936" value="1936">1936</option>
<option label="1935" value="1935">1935</option>
<option label="1934" value="1934">1934</option>
<option label="1933" value="1933">1933</option>
<option label="1932" value="1932">1932</option>
<option label="1931" value="1931">1931</option>
<option label="1930" value="1930">1930</option>
<option label="1929" value="1929">1929</option>
<option label="1928" value="1928">1928</option>
<option label="1927" value="1927">1927</option>
<option label="1926" value="1926">1926</option>
<option label="1925" value="1925">1925</option>
<option label="1924" value="1924">1924</option>
<option label="1923" value="1923">1923</option>
<option label="1922" value="1922">1922</option>
</select></td>
	 </tr>
	 <tr width="268px">
         <td  ><label class="form-label4Name1" for="EmailId">Blood Group</label></td>
		 <td><select class="select1" name="bloodGrop" id="bldG" > 
			<!--<option value="NULL">--------------------------------</option>-->
			<option style="width:130px;">Select</option>
			<option label="A+" value="A+">A+</option>
			<option label="A-" value="A-">A-</option>
			<option label="B+" value="B+">B+</option>
			<option label="B-" value="B-">B-</option>
			<option label="o+" value="o+">o+</option>
			<option label="o-" value="o-">o-</option>
			<option label="AB+" value="AB+">AB+</option>
			<option label="AB-" value="AB-">AB-</option>

			</select></td>
				 
	 </tr>
	 <tr width="268px">
         <td><label class="form-label4Name1" for="EmailId"><font color=orange>Address</font></label></td>
		 <td colspan=5 align=left>&nbsp;</td>
		
	 </tr>
	 <tr width="268px">
         <td  ><label class="form-label4Name" for="Add1">Line1</label></td>
		 <td><input type="text"  value="" name="Add1" id="Add1" /></td>
		 <td ><label class="form-label4Name" for="Add2">Line2</label></td>
		 <td colspan=3><input type="text" value="" name="Add2" id="Add2" style="width:160px"/></td>
		 
	 </tr>
	 <tr width="268px">
         <td  ><label class="form-label4Name" for="PosID">PostalCode</label></td>
		 <td><input type="text"  value="" name="PosID" id="PosID" /></td>
		 <td ><label class="form-label4Name" for="lId">Locality></label></td>
		 <td colspan=3><input type="text" value="" name="lId" id="lId" style="width:160px"/></td>
		 
	 </tr>
	 <tr width="268px">
         <td  ><label class="form-label4Name" for="cId">City</label></td>
		 <td><input type="text"  value="" name="cId" id="cId" /></td>
		 <td ><label class="form-label4Name" for="sId">State</label></td>
		 <td colspan=3><select class="select1" name="sId" id="sId" > <!-- onChange="processFieldValueChange('country')"-->
<!--<option value="NULL">--------------------------------</option>-->
<option style="width:130px;">Select</option>

<option value="AP">Andhra Pradesh</option>
<option value="AR">Arunachal Pradesh</option>
<option value="AS">Assam </option>
<option value="BR">Bihar</option>
<option value="CH">Chhattisgarh </option>
<option value="DL">Delhi</option>
<option value="GA">Goa </option>
<option value="GJ">Gujarat </option>
<option value="HR">Haryana </option>
<option value="HP">Himachal Pradesh</option>
<option value="JK">Jammu and Kashmir</option>
<option value="JH">Jharkhand </option>
<option value="KL">Kerala </option>
<option value="KA">karnataka</option>
<option value="MP">Madhya pradesh</option>
<option value="MH">Maharashtra </option>
<option value="MN">Manipur </option>
<option value="ML">Megalaya </option>
<option value="MZ">Mizoram </option>
<option value="ND">Nagaland </option>
<option value="OR">Orissa </option>
<option value="PB">Punjab </option>
<option value="RJ">Rajashthan </option>
<option value="SK">Sikkim </option>
<option value="TN">Tamil Nadu</option>
<option value="TP">Tripura </option>
<option value="UP">Uttar Pradesh</option>
<option value="UT">Uttarakhand </option>
<option value="WB">West Bengal</option>





</select ></td>
		 
	 </tr>

	  <tr width="268px">
         <td colspan=2 align=right><label class="form-label4Name1" for="cv">Attach Resume</label></td> 
		 <td colspan=4 align=left><input type="file" value="" name="cv" id="cv" style="width:160px"/></td>
		
	 </tr>
	 <tr width="268px">
		 <td colspan=2 align=right>&nbsp;</td>
         <td colspan=4 align="left"><label class="form-label4Name1" for="submit"><input type="submit" value="Submit" name="Submit" id="submit" style="background: #675c01; font-size: 18px; border-radius:5px;font-family:Bradley Hand ITC; border:0;color:#FFFFFF  " /></td>
		 

		
	 </tr>
	  </table> 

	 

           
</form>
</div>

    

    




 

</div> </div>  <div class="footer-top"></div>
</div><div class="footer"><br />
About &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Careers &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="gallery.html" style="text-decoration: none; color:#FFF;">Gallery</a><br />
      <br />
      <span class="style1">&copy; Fuel 24x7 Labs</div>





</body>
</html>
