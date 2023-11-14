<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<head>
    <title>Currency</title>
</head>
<body>
<s:form action="index">
    <table>
        <tr>
            <td></td>
            <td>Currency</td>
            <td>From</td>
            <td>To</td>
            <td>Actual value</td>
        </tr>
        <s:iterator var="item" value="currencies" status="status">
            <tr>
                <td><s:property value="status.count"/></td>
                <td><s:select name="currencies[%{#status.index}].name" list="currencyNames" cssErrorClass="has-error"/></td>
                <td><s:textfield name="currencies[%{#status.index}].from" maxlength="10" cssErrorClass="has-error"/></td>
                <td><s:textfield name="currencies[%{#status.index}].to" maxlength="10" cssErrorClass="has-error"/></td>
                <td>
                    <s:if test="#item.actual != null">
                        <span style="color:<s:property value='colour[#item.compare]'/>"><s:property value="#item.actual"/></span>
                    </s:if>
                </td>
                <td><a href="index.action?deleteCurrency=<s:property value='#status.count'/>" style="color:red">Delete</a></td>
            </tr>
        </s:iterator>
    </table>
    <s:submit name="getActual" value="Get Actual Value" cssClass="btn btn-primary pull-right"/>
    <s:submit name="getFromFile" value="Load Values" cssClass="btn btn-primary pull-right"/>
    <s:submit name="writeToFile" value="Save Values" cssClass="btn btn-primary pull-right"/>
    <s:submit name="addCurrency" value="Add New Currency" cssClass="btn btn-primary pull-right"/>
</s:form>
</body>