<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head>
    <title> Khai báo dữ liệu tôn đầu kỳ</title>
    <meta name="heading" content="Khai báo dữ liệu tôn đầu kỳ"/>
</head>
<body>
<c:url var="url" value="/whm/product/importinit.html"/>
<c:url var="backUrl" value="/whm/home.html"/>
</br>
<form:form commandName="item" action="${url}" method="post" id="itemForm" class="form-horizontal" novalidate="novalidate" enctype="multipart/form-data">

    <div id="container-fluid data_content_box">
        <div class="row-fluid data_content">
            <c:if test="${!empty errorMessage}">
                <div class="alert alert-error">
                    <a class="close" data-dismiss="alert" href="#">x</a>
                        ${errorMessage}
                </div>
                <div style="clear:both;"></div>
            </c:if>
            <c:if test="${!empty successMessage}">
                <div class="alert alert-success">
                    <a class="close" data-dismiss="alert" href="#">x</a>
                        ${successMessage}
                </div>
                <div style="clear:both;"></div>
            </c:if>

            <div class="content-header">
                Khai báo dữ liệu tôn đầu kỳ
            </div>
            <div class="clear"></div>

            <div class="row-fluid">
                <div class="control-group">

                    <label class="control-label">
                        <fmt:message key="whm.warehouse"/>
                    </label>
                    <div class="controls">
                        <select name="warehouse.warehouseID" style="width: 150px;">
                            <c:forEach items="${warehouses}" var="warehouse">
                                <option value="${warehouse.warehouseID}">${warehouse.name}</option>
                            </c:forEach>
                        </select>
                        <form:errors path="warehouse" cssClass="error-inline-validate"/>
                    </div>

                    <label class="control-label">
                        Ngày chốt
                    </label>
                    <div class="controls">
                        <div class="input-append date" >
                            <input name="initDate" id="initDate" class="prevent_type text-center width2" value="" type="text" />
                            <span class="add-on" id="initDateIcon"><i class="icon-calendar"></i></span>
                        </div>
                    </div>

                    <label class="control-label">
                        <fmt:message key="label.file"/>
                    </label>
                    <div class="controls">
                        <input type="file" name="dataFile"/>
                        <a href="<c:url value="/files/importtemplate/templateInitImportProduct.xls"/>"> <fmt:message key="label.file.example"/></a>
                    </div>
                    <div class="controls">
                        <form:hidden path="crudaction" id="crudaction" value="import"/>
                        <a onclick="submitForm('itemForm');" class="btn btn-success btn-green" style="cursor: pointer;">
                            <fmt:message key="button.import"/>
                        </a>
                        <div style="display: inline">
                            <a href="${backUrl}" class="cancel-link">
                                <fmt:message key="button.cancel"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>
<script type="text/javascript">
    $(document).ready(function(){
        var initDateVar = $("#initDate").datepicker({
            format: 'dd/mm/yyyy',
            onRender: function(date){
            }}).on('changeDate', function(ev) {
                    initDateVar.hide();
                }).data('datepicker');
        $('#initDateIcon').click(function() {
            $('#initDate').focus();
            return true;
        });
    });
</script>
</body>

