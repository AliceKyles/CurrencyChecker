<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<head>
    <title>ReadWriteData.Currency</title>
</head>
<body>
    <s:form action="index">
        <s:textfield name="currency" label="ReadWriteData.Currency"/>
        <s:textfield name="value" label="Value"/>
    </s:form>
</body>