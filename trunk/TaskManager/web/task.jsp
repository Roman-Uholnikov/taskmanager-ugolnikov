<%-- 
    Document   : task
    Created on : 28.11.2012, 14:30:26
    Author     : roman
--%>

<%@page import="Control.Exceptions.UserInputException"%>
<%@page import="Control.Exceptions.UserAutentificationException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UserInputException userException = (UserInputException)request.getAttribute("userException");%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=Windows-1251">
<title>Лекции :: Дистанционное образование Донецкого политехнического техникума</title>
<link rel="stylesheet" href="css/style.css">
<LINK REL="SHORTCUT ICON" href="favicon.ico">
<meta http-equiv="content-type" content="text/html; charset=Windows-1251">
<Meta name="Author" Content="Ugolnikov Roman">
<Meta name="Description" Content="Сайт дистанционного образования Донецкого политенического техникума">
<Meta name="Keywords" Content="ДПТ,Донецкий политехнический техникум,ДонПТ,политех,дистанционное,образование,лекции,лабораторные,практические,скачать лекции,литература,учебники">

</head>
<body > 

<table WIDTH="100%" BORDER="0" RULES="none"  BGCOLOR="#FFFFFF" CELLSPACING="0" CELLPADDING="0"> <!--O-->
<tr><td>		<!--O-->
<table WIDTH="100%" BORDER="0" BGCOLOR="#FFFFFF" CELLSPACING="2" CELLPADDING="0"><!--рисунок-->
<tr><td>
 <td WIDTH="785"><IMG SRC="pic/ban.jpg" BORDER="0" ALIGN="left" WIDTH="782" HEIGHT="150" ALT="Дистанционное образование донецкого политехнического техникума"></td>
 <td><IMG SRC="pic/dpt.gif" BORDER="0" ALIGN="right" WIDTH="120" HEIGHT="118" ALT="Эмблема ДПТ"></td>
</tr>
</table></td></tr>		<!--O-->
<tr><td>		<!--O-->

<table WIDTH="100%" BORDER="0" BGCOLOR="#FFFFFF" CELLPADDING="4">
<tr>
 <td WIDTH="252" VALIGN="TOP">
	<Table WIDTH="251" BORDER="0" BGCOLOR="#FFFFFF" CELLPADDING="0" CELLSPACING="0" RULES="none"><!-- левая панель-->
	<tr>
		<td WIDTH="39" HEIGHT="39" VALIGN=BOTTOM><img SRC="pic/u15.gif" BORDER="0" WIDTH="40" HEIGHT="40"></td>
		<td HEIGHT="39" BACKGROUND="pic/up.gif" >&nbsp</td>
		<td WIDTH="39" HEIGHT="39" VALIGN=BOTTOM><img SRC="pic/u12.gif" BORDER="0" WIDTH="40" HEIGHT="40"></td>
	</tr>
	<tr>
		<td BACKGROUND="pic/left.gif" WIDTH="39">
		</td>
		<td BGCOLOR="#050e2f">


<table width="100%" VALIGN="TOP" BORDER="0" BGCOLOR="#050e2f" CELLSPACING="2">	<!--вход-->
<tr>
<td COLSPAN="2">
<FORM ACTION="lessons.php" name="logpw" method=post><INPUT TYPE=text SIZE=24 id="login" NAME="login" MAXLENGTH=20 VALUE="login" onFocus="javascript:highlight(this.id)"></td>
</tr>
<tr>
<td COLSPAN="2"><INPUT TYPE=password NAME="password" SIZE=24 MAXLENGTH=20 id="password" VALUE="password" onFocus="javascript:highlight(this.id)"></td>
</tr>
<tr>
<td><a href="registration.php"><i>регистрация</i></a></td>
<td><INPUT TYPE=submit VALUE="вход"></td></FORM>
</tr>
<tr>
 <td COLSPAN="2"><hr color="#f2f779" width="100%" size="4">
 </td>
</tr>
<!--buttons--> 
<tr> 
<td COLSPAN="2" align="center"><a href="lessons.php?link=lessons"><img SRC="pic/predmet.gif" BORDER="0" WIDTH="160" HEIGHT="35" alt="лекции"></a>
</TD>
</tr>
<tr>
<td COLSPAN="2" align="center">
<a href=lessons.php?order=tema class="vtb"><h5>отсортировать <br> по теме</h5></a></td></tr>


<tr> 
<td COLSPAN="2" align="center"><a href="labs.php?link=labs"><img SRC="pic/labs.gif" BORDER="0" WIDTH="160" HEIGHT="35"alt="лабораторные и практические"></a>
</TD>
</tr>
<tr> 
<td COLSPAN="2" align="center"><a href="books.php?link=books"><img SRC="pic/books.gif" BORDER="0" WIDTH="160" HEIGHT="35" alt="литература"></a>
</TD>
</tr>
</tr>
<!--buttons-->
</table>
		</td>
		<td BACKGROUND="pic/right.gif" WIDTH="39">
		</td>
	</tr>
	<tr>
		<td WIDTH="39"><img SRC="pic/u14.gif" BORDER="0" WIDTH="40" HEIGHT="40"></td>
		<td BACKGROUND="pic/down.gif" HEIGHT="39">&nbsp</td>
		<td WIDTH="39"><img SRC="pic/u13.gif" BORDER="0" WIDTH="40" HEIGHT="40"></td>
	</tr>
	</Table>
 </td>
 
 <td VALIGN="TOP"> 
	<Table CELLPADDING="10" width="100%" border="0">					<!-- правая часть сайта-->
	<tr>
    <td>
	<h2 align="right">25.11.12 <strong> Уольников Роман Владимирович</strong><b></b></h2>

<FORM ACTION="lessons.php" id="delete" name="delete" method=post><input type=hidden name=table value=lekcii><input type=hidden name="param[]" value="numb"><input type=hidden name="param[]" value="predmet"><input type=hidden name=predmet value=1>
<table BGCOLOR="#ffffff" width="100%" CELLPADDING="0" class="lec" FRAME="VOID" RULES="none" border="0">
<tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">дата:</a>
  </td>
  <td class="dataBgColor"> 
  	<p class="header">21:30 21.10.2012</p>
    </td>
 </tr>
 <tr bgcolor="#eeeeee">
   <td width="200" class="task_list_left">
     инициатор:
   </td>
  <td bgcolor="#eeeeee"> <a class="task_list" href="lessons.php?lekciya=2"><b class="lec_list"> Карчкова Валентина Афанасиевна (бухгалтерия)</b></a>
  </td>
  
<tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">приоритет:</a>
  </td>
  <td class="dataBgColor"> 
  	<a class="header">средний</a>
    </td>
 </tr>
<tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">статус </a>
  </td>
  <td class="dataBgColor"><a class="header">в работе</a>
    </td>
 </tr>
 <tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">контактынй телефон </a>
  </td>
  <td class="dataBgColor"><a class="header">066660133</a>
    </td>
 </tr>
  <tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">получатель </a>
  </td>
  <td class="dataBgColor"><a class="header">Группа развития компютерных технологий</a>
    </td>
 </tr>
  <tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">заголовок </a>
  </td>
  <td class="dataBgColor"><a class="header">Принетр не печатает</a>
    </td>
 </tr>
  <tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">текст заявки </a>
  </td>
  <td class="dataBgColor"><a class="header">Мальчики ну сколько можно. Я его уже трушу трушу. Да , там еще бумажка застряла, придите пожалуйста, вытащите, а то я немогу</a>
    </td>
 </tr>
   <tr bgcolor="#eeeeee">
  <td width="200" align="center" bgcolor="#eeeeee" class="LowPriority"><a class="task_list_left">заметки</a>
  </td>
  <td class="dataBgColor"><a class="header">21:40 21.12.12 Бумагу достал. Необходима заправка картриджа</a><a class="task_list" href="lessons.php?lekciya=2"><b class="lec_list"> Куций Вадим Анатолиевич (Группа Развития и Технической Потдержки Информационных технологий)</b></a>
  
  <a class="header">21:42 21.12.12 !!!!!!!Попытался заправить - необходима замена барабана</a><a class="task_list" href="lessons.php?lekciya=2"><b class="lec_list"> Куций Вадим Анатолиевич (Группа Развития и Технической Потдержки Информационных технологий)</b></a>
    </td>
 </tr>
  
  
 
 <tr bgcolor="#eeeeee">
   <td width="200" align="center" class="MiddlePriority">
     <a href="lessons.php?lekciya=2" class="lec_list"><numb>21:30 21.10.2012</numb>
       <br>Марченко Елена
       </a>
   </td>
  <td bgcolor="#eeeeee"> <a class="task_list" href="lessons.php?lekciya=2"><b class="lec_list"> Краска в принтере заканчивается</b><br>
    Мальчики ну солько можно я уже трушу его трушу, а он все не печатает. Да и еще, там страница...</a>
  </td>
 </tr>
 <tr bgcolor="#eeeeee">
   <td width="200" align="center" class="DoneTadk">
     <a href="lessons.php?lekciya=2" class="lec_list"><numb>21:30 21.10.2012</numb>
       <br>Марченко Елена
       </a>
   </td>
  <td bgcolor="#eeeeee" class="DoneTadk"> <a class="task_list" href="lessons.php?lekciya=2"><b class="lec_list"> Краска в принтере заканчивается</b><br>
    Мальчики ну солько можно я уже трушу его трушу, а он все не печатает. Да и еще, там страница...</a>
  </td>
 </tr>
<tr  bgcolor="#dddddd">
  <td width="200" align="center" class="MiddlePriority"><a href="lessons.php?lekciya=3" class="lec_list">
    <numb>12:12 13.10.2012</numb> <br> Куций Василий
  </a></td>
  <td bgcolor="#eeeeee"> <a class="lec_list" href="lessons.php?lekciya=3"><b> Лекция по HTTP</b></a></td></tr>
<tr bgcolor="#eeeeee">
  <td width="200" align="center" class="HightPriority"><a href="lessons.php?lekciya=4" class="lec_list">
    <numb>16:01 11.4.2012</numb> <br> Шарапов Анатолий</a></td>
  <td> <a class="lec_list" href="lessons.php?lekciya=4"><b> С чего начинать?</b></a></td></tr>
<tr  bgcolor="#dddddd">
  <td width="200" align="center" bgcolor="#C5FBA4"><a href="lessons.php?lekciya=6" class="lec_list">Лекция №_<numb>6</numb></a></td>
  <td bgcolor="#C5FBA4"> <a class="lec_list" href="lessons.php?lekciya=6"><b> picture</b></a></td></tr>
<tr bgcolor="#eeeeee">
  <td width="200" align="center"><a href="lessons.php?lekciya=8" class="lec_list">Лекция №_<numb>8</numb></a></td>
  <td> <a class="lec_list" href="lessons.php?lekciya=8"><b> теги &lt;br&gt; его свойства</b></a></td></tr>
<tr  bgcolor="#dddddd" class="lec_list">
  <td width="200" align="center"><a href="lessons.php?lekciya=10" class="lec_list">Лекция №_<numb>10</numb></a></td>
  <td> <a class="lec_list" href="lessons.php?lekciya=10"><b> Спец символы &quot; ` ~ &lt; &gt; / ' </b></a></td></tr>
<tr bgcolor="#eeeeee">
  <td width="200" align="center"><a href="lessons.php?lekciya=11" class="lec_list">Лекция №_<numb>11</numb></a></td>
  <td> <a class="lec_list" href="lessons.php?lekciya=11"><b> предмет</b> </a></td></tr>
</table></FORM> </td>
</tr>
</table>

</td></tr>		<!--O-->
</table>
</body>