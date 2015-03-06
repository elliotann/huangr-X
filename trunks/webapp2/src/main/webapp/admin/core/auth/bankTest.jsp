<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<HEAD><TITLE>交易入口</TITLE></HEAD><BODY>
<form name=myForm method=POST action='role.do?toTest2'>
<input type=hidden name="MerId" value="808080290000001"/> 
<input type=hidden name='Version' value='20141120'>
<input type=hidden name='TransDate' value='20150305'>
<input type=hidden name='TransType' value='0001'>
<input type=hidden name='TransAmt' value='000013200000'>
<input type=hidden name='OrdId' value='0000004111100124'>
<input type=hidden name='CuryId' value='156'>
<input type=hidden name='ChkValue' value=''>
<input type=hidden name='BgRetUrl' value='http://www.example.com/pay/Bgreturn.jsp'>
<input type=hidden name='PageRetUrl' value='http://www.example.com/pay/Bgreturn.jsp'>
<input type=hidden name='Priv1' value='Memo'>
<input type=hidden name='GateId' value=''>
</form>
<script language=JavaScript>
document.myForm.submit();
</script>
</body></html>]