<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<head>
    <title>Currency</title>
    <style type="text/css">
        .errorMessage {
            font-weight: bold;
            color: red;
        }
        .errField {
            border: 2px solid red;
        }
    </style>
</head>
<body>
<s:form action="index">
    <s:actionerror/>
    <table>
        <s:if test="!currencies.empty">
            <tr>
                <td></td>
                <td>Currency</td>
                <td>From</td>
                <td>To</td>
                <td>Actual value</td>
            </tr>
        </s:if>
        <s:iterator var="item" value="currencies" status="status">
            <tr>
                <s:hidden name="currencies[%{#status.index}].actual"/>
                <s:hidden name="currencies[%{#status.index}].compare"/>
                <td><s:property value="status.count"/></td>
                <td><s:select name="currencies[%{#status.index}].name" list="currencyNames" cssErrorClass="errField"/></td>
                <td><s:textfield name="currencies[%{#status.index}].from" maxlength="10" cssErrorClass="errField"/></td>
                <td><s:textfield name="currencies[%{#status.index}].to" maxlength="10" cssErrorClass="errField"/></td>
                <td>
                    <s:if test="#item.actual != null">
                        <span style="color:<s:property value='colour[#item.compare]'/>"><s:property value="#item.actual"/></span>
                    </s:if>
                </td>
                <td><s:submit type="button" name="delete" value="%{#status.index}" label="Delete"/></td>
            </tr>
        </s:iterator>
    </table>
    <div style="margin-left: 5px">
        <s:submit name="getActual" value="Check"/>
        <s:submit name="getFromFile" value="Load"/>
        <s:submit name="writeToFile" value="Save"/>
        <s:submit name="addCurrency" value="Add"/>
    </div>
</s:form>
</body>