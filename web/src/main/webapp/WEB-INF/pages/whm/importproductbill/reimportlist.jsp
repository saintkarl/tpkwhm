<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.banvien.tpk.core.Constants" %>
<html>
<head>
    <title><fmt:message key="import.reimport.product.title"/></title>
    <meta name="heading" content="<fmt:message key='import.reimport.product.title'/>"/>
</head>
<c:url var="urlForm" value="/whm/importproductbill/reimportlist.html"></c:url>
<c:url var="viewUrl" value="/whm/importproductbill/reimportview.html"></c:url>
<c:url var="editUrl" value="/whm/importproductbill/reimport.html"></c:url>
<body>
<div class="row-fluid data_content">
    <div class="content-header"><fmt:message key="import.reimport.product.title"/></div>
    <div class="clear"></div>
    <c:if test="${not empty messageResponse}">
        <div class="alert alert-${alertType}">
            <button aria-hidden="true" data-dismiss="alert" class="close" type="button">x</button>
                ${messageResponse}
        </div>
    </c:if>
    <div class="report-filter">
        <form:form commandName="items" action="${urlForm}" id="listForm" method="post" autocomplete="off" name="listForm">
            <table class="tbReportFilter" >
                <caption><fmt:message key="label.search.title"/></caption>
                <tr>
                    <td class="label-field" ><fmt:message key="label.number"/></td>
                    <td><form:input path="code" size="25" maxlength="45" /></td>
                    <td class="label-field" ></td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td class="label-field"><fmt:message key="label.fromdate"/></td>
                    <td>
                        <div class="input-append date" >
                            <fmt:formatDate var="ngayKeKhaiFrom" value="${items.fromDate}" pattern="dd/MM/yyyy"/>
                            <input name="fromDate" id="effectiveFromDate" class="prevent_type text-center width2" value="${ngayKeKhaiFrom}" type="text" />
                            <span class="add-on" id="effectiveFromDateIcon"><i class="icon-calendar"></i></span>
                        </div>
                    </td>
                    <td class="label-field"><fmt:message key="label.todate"/></td>
                    <td>
                        <div class="input-append date" >
                            <fmt:formatDate var="ngayKeKhaiTo" value="${items.toDate}" pattern="dd/MM/yyyy"/>
                            <input name="toDate" id="effectiveToDate" class="prevent_type text-center width2" value="${ngayKeKhaiTo}" type="text" />
                            <span class="add-on" id="effectiveToDateIcon"><i class="icon-calendar"></i></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="label-field"><fmt:message key="import.material.supplier"/></td>
                    <td>
                        <form:select path="supplierID" cssStyle="width: 360px;">
                            <form:option value="-1">Tất cả</form:option>
                            <c:forEach items="${customers}" var="customer">
                                <form:option value="${customer.customerID}">${customer.name} - ${customer.province.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td class="label-field"><fmt:message key="import.material.receive.warehouse"/></td>
                    <td>
                        <form:select path="warehouseID">
                            <form:option value="-1">Tất cả</form:option>
                            <form:options items="${warehouses}" itemValue="warehouseID" itemLabel="name"/>
                        </form:select>
                    </td>
                </tr>
                <tr style="text-align: center;">
                    <td colspan="4">
                        <a id="btnFilter" class="btn btn-primary " onclick="submitForm();"><i class="icon-refresh"></i> <fmt:message key="label.search"/> </a>
                    </td>
                </tr>
            </table>
            <div class="clear"></div>
            <div id="infoMsg"></div>
            <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${urlForm}"
                           partialList="true" sort="external" size="${items.totalItems }"
                           uid="tableList" excludedParams="crudaction"
                           pagesize="${items.maxPageItems}" export="false" class="tableSadlier table-hover">
                <display:caption><fmt:message key='import.reimport.product.title'/></display:caption>
                <display:column headerClass="table_header_center" sortable="false" title="STT" class="text-center" style="width: 2%;">
                    ${tableList_rowNum}
                </display:column>
                <display:column headerClass="table_header_center" property="code" titleKey="label.number" class="text-center" style="width: 10%;"/>
                <display:column headerClass="table_header large" property="customer.name" titleKey="import.material.supplier" style="width: 15%;"/>
                <display:column headerClass="table_header large" property="warehouse.name" titleKey="import.material.receive.warehouse" sortName="warehouse.name" sortable="true" style="width: 15%;"/>
                <display:column headerClass="table_header_center" sortName="importDate" sortable="true" titleKey="import.date" class="text-center" style="width: 8%;">
                    <fmt:formatDate value="${tableList.importDate}" pattern="dd/MM/yyyy"/>
                </display:column>
                <display:column headerClass="table_header_center" sortName="status" sortable="true" titleKey="label.status" style="width: 10%;text-align:center;">
                    <c:choose>
                        <c:when test="${tableList.status == Constants.CONFIRMED}"><fmt:message key="label.confirmed"/></c:when>
                        <c:when test="${tableList.status == Constants.REJECTED}"><fmt:message key="label.rejected"/></c:when>
                        <c:when test="${tableList.status == Constants.WAIT_CONFIRM}"><fmt:message key="label.wait.confirm"/></c:when>
                        <c:otherwise>-</c:otherwise>
                    </c:choose>
                </display:column>
                <c:set var="allowEdit" value="${tableList.editable}"/>
                <c:set var="allowDelete" value="${tableList.deletable}"/>
                <security:authorize ifAnyGranted="ADMIN">
                    <c:set var="allowDelete" value="true"/>
                    <c:set var="allowEdit" value="false"/>
                </security:authorize>
                <display:column headerClass="table_header_center" sortName="soTien" sortable="true" titleKey="label.options" style="width: 10%;text-align:center;">
                    <a href="${viewUrl}?pojo.importProductBillID=${tableList.importProductBillID}" class="icon-eye-open tip-top" title="<fmt:message key="label.view"/>"></a>
                    <c:if test="${allowEdit}">
                        <span class="separator"></span>
                        <a href="${editUrl}?pojo.importProductBillID=${tableList.importProductBillID}" class="icon-edit tip-top" title="<fmt:message key="label.edit"/>"></a>
                    </c:if>
                    <c:if test="${allowDelete}">
                        <span class="separator"></span>
                        <a name="deleteLink" onclick="warningDelete(this)" id="${tableList.importProductBillID}" href="#" class="icon-remove tip-top" title="<fmt:message key="label.delete"/>"></a>
                    </c:if>
                </display:column>
                <display:setProperty name="paging.banner.item_name" value="Phiếu nhập thành phẩm"/>
                <display:setProperty name="paging.banner.items_name" value="Phiếu nhập thành phẩm"/>
                <display:setProperty name="paging.banner.placement" value="bottom"/>
                <display:setProperty name="paging.banner.no_items_found" value=""/>
            </display:table>
            <form:hidden path="crudaction" id="crudaction"/>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        var effectiveToDateVar = $("#effectiveToDate").datepicker({
            format: 'dd/mm/yyyy',
            onRender: function(date){
            }}).on('changeDate', function(ev) {
                    effectiveToDateVar.hide();
                }).data('datepicker');
        var effectiveFromDateVar = $("#effectiveFromDate").datepicker({
            format: 'dd/mm/yyyy',
            onRender: function(date){
            }}).on('changeDate', function(ev) {
                    effectiveFromDateVar.hide();
                }).data('datepicker');
        $('#effectiveToDateIcon').click(function() {
            $('#effectiveToDate').focus();
            return true;
        });
        $('#effectiveFromDateIcon').click(function() {
            $('#effectiveFromDate').focus();
            return true;
        });


        $("#btnFilter").click(function(){
            $("#crudaction").val("search");
            $("#listForm").submit();
        });
    });
</script>
</body>
</html>