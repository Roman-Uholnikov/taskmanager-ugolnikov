<html>
<body><br><br>
    <form action="xxx" method="GET" name="search" >
        <table name="" align="center" width="1000" border="3">
            <tr>
                <td colspan="3" align="center">
                    <H1>XXX Параметры поиска</H1>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="XXX"> Параметр
                    
                </td>
                <td>
                    <a class="XXX">значение параметра (оставит пустым не используемые)
                </td>
                <td>
                    подсказка
                </td>
            </tr>
            <tr><!-- дата создания start -->
                <td>
                    <a class="XXX"> дата создания
                    
                </td>
                <td>
                    <a class="XXX"> с </a><input class="xxx" name="createdsince" size="8"><a class="XXX"> по </a><input class="xxx" name="createdupto" size="8">    
                </td>
                <td>
                    диапазон времени в который была создана заявка (в формате 22.12.2012)
                </td>
            </tr><!-- дата создания end -->
            <tr><!-- дата закрытия start -->
                <td>
                    <a class="XXX"> дата закрытия
                    
                </td>
                <td>
                    <a class="XXX"> с </a><input class="xxx" name="closedsince" size="8"><a class="XXX"> по </a><input class="xxx" name="closedupto" size="8">    
                </td>
                <td>
                    диапазон времени в который заявка была закрыта(в формате 22.12.2012)
                </td>
            </tr><!-- дата закрытия end -->
            <tr> <!-- инициатор start -->
                <td>
                    <a class="XXX"> инициатор
                    
                </td>
                <td>
                   <input class="xxx" name="owner">
                </td>
                <td>
                    введите фамилию, или фрагмент фамилии. (Для фамили "Сумасбродов" можно ввести сума, бродов, масброд)
                </td>
            </tr><!-- инициатор end -->
            <tr> <!-- группавая принадлежность start -->
                <td>
                    <a class="XXX"> группавая принадлежность заявки</a>
                    
                </td>
                <td>
                    <input type="radio" name="froupflag" value="any" checked="true">любая заявка<br>
                    <input type="radio" name="froupflag" value="false">индивидуальная<br>
                    <input type="radio" name="froupflag" value="true">группавая<br>
                </td>
                <td>
                    <a class="xxx">была ли заявка адресована индивидуально человеку, либо группе (отделу)</a>
                </td>
            </tr><!-- группавая принадлежность end -->
            <tr> <!-- локализация start -->
                <td>
                    <a class="XXX">локализация проблемы</a>
                    
                </td>
                <td>
                    <input type="text" name="localization" size="35">
                </td>
                <td>
                    <a>место (этаж, кабинет, компьютер, принтер и т.д.) где возникла проблема.</a>
                </td>
            </tr><!-- локализация end -->    
            <tr> <!-- коментарии start -->
                <td>
                    <a class="XXX">фрагмент из коментариев</a>
                    
                </td>
                <td>
                    <input type="text" name="localization" size="35">
                </td>
                <td>
                    <a class="xxx">любое слово, или фраза, написанная при редактировании коментариев или при закрытии заявки</a>
                </td>
            </tr><!-- коментарии end -->    
            <tr> <!-- статус start -->
                <td>
                    <a class="XXX"> статус</a>
                    
                </td>
                <td>
                    <input type="radio" name="status" value="any" checked="true">любая заявка<br>
                    <input type="radio" name="status" value="closed">закрытая<br>
                    <input type="radio" name="status" value="open">в работе<br>
                </td>
                <td>
                    <a class="xxx">состояние заявки на текущий момент</a>
                </td>
            </tr><!-- статус end -->
            <tr> <!-- заголовок start -->
                <td>
                    <a class="XXX">фрагмент из заголовка</a>
                    
                </td>
                <td>
                    <input type="text" name="title" size="35">
                </td>
                <td>
                    <a class="xxx">любое слово, или фраза, написанная в теме заявки</a>
                </td>
            </tr><!-- заголовок end -->   
            <tr> <!-- заголовок start -->
                <td>
                    <a class="XXX">фрагмент из текста заявки заявки</a>
                    
                </td>
                <td>
                    <input type="text" name="text" size="35">
                </td>
                <td>
                    <a class="xxx">любое слово, или фраза, написанная в тексте заявки</a>
                </td>
            </tr><!-- заголовок end -->  
            <tr>
                <td colspan="3" align="center">
                    <input type="submit">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>