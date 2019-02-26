<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="quick.import.product.title"/></title>
    <meta name="heading" content="<fmt:message key="quick.import.product.title"/>"/>
</head>
<c:url var="url" value="/whm/product/quickimport.html"/>
<c:url var="backUrl" value="/whm/home.html"/>
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
                <fmt:message key="quick.import.product.title"/>
            </div>
            <div class="clear"></div>

            <div class="row-fluid">
                <div class="control-group">
                    <label class="control-label">
                        <fmt:message key="label.file"/>
                    </label>
                    <div class="controls">
                        <input type="file" name="dataFile"/>
                        <a href="<c:url value="/files/importtemplate/backSteelQuickImport.xls"/>"> <fmt:message key="label.file.example"/></a>
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


