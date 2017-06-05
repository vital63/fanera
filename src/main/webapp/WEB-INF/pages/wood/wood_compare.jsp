<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Meta -->
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="keywords" content="Wood compare">
        <meta name="robots" content="all">
            
        <title>Wood compare</title>
            
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="../resources/assets/css/bootstrap.min.css">
            
        <!-- Customizable CSS -->
        <link rel="stylesheet" href="../resources/assets/css/main.css">
        <link rel="stylesheet" href="../resources/assets/css/green.css">
        <link rel="stylesheet" href="../resources/assets/css/owl.carousel.css">
        <link rel="stylesheet" href="../resources/assets/css/owl.transitions.css">
        <link rel="stylesheet" href="../resources/assets/css/animate.min.css">
            
        <!-- Demo Purpose Only. Should be removed in production -->
        <link rel="stylesheet" href="../resources/assets/css/config.css">
            
        <link href="../resources/assets/css/green.css" rel="alternate stylesheet" title="Green color">
        <link href="../resources/assets/css/blue.css" rel="alternate stylesheet" title="Blue color">
        <link href="../resources/assets/css/red.css" rel="alternate stylesheet" title="Red color">
        <link href="../resources/assets/css/orange.css" rel="alternate stylesheet" title="Orange color">
        <link href="../resources/assets/css/navy.css" rel="alternate stylesheet" title="Navy color">
        <link href="../resources/assets/css/dark-green.css" rel="alternate stylesheet" title="Darkgreen color">
        <!-- Demo Purpose Only. Should be removed in production : END -->
            
        <!-- Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>
            
        <!-- Icons/Glyphs -->
        <link rel="stylesheet" href="../resources/assets/css/font-awesome.min.css">
            
        <!-- Favicon -->
        <link rel="shortcut icon" href="../resources/assets/images/favicon.ico">
            
        <!-- HTML5 elements and media queries Support for IE8 : HTML5 shim and Respond.js -->
        <!--[if lt IE 9]>
            <script src="../resources/assets/js/html5shiv.js"></script>
            <script src="../resources/assets/js/respond.min.js"></script>
        <![endif]-->
            
            
            
    </head>
    <body>
        
        <div class="wrapper">
        
            
            <!-- ============================================================= TOP NAVIGATION ============================================================= -->
            <%@include file="/WEB-INF/pages/in/nav.jsp" %>        
            <!-- ============================================================= TOP NAVIGATION : END ============================================================= -->       <!-- ============================================================= HEADER ============================================================= -->
            <%@include file="/WEB-INF/pages/in/header.jsp" %>
            <!-- ============================================================= HEADER : END ============================================================= --><div class="animate-dropdown"><!-- ========================================= BREADCRUMB ========================================= -->
    
                
                
                <div id="top-mega-nav">
                    <div class="container">
                        <nav>
                            <ul class="inline">
                                <!--                <li class="dropdown le-dropdown">
                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                        <i class="fa fa-list"></i> shop by department
                                                    </a>
                                                    <ul class="dropdown-menu">
                                                        <li><a href="vmc.htm">Vertical machine centers</a></li>
                                                        <li><a href="#">Horizontal machine centers</a></li>
                                                        <li><a href="#">Lathe CNC</a></li>
                            
                                                    </ul>
                                                </li>-->
                                <%@include file="/WEB-INF/pages/in/dropdown_le_dropdown.jsp" %>
    
                                <li class="breadcrumb-nav-holder"> 
                                    <ul>
                                        <!--<li class="breadcrumb-item">-->
                                        <%@include file="/WEB-INF/pages/in/breadcrumb_item.jsp" %>
                                        <!--</li>--> 
                                        <li class="breadcrumb-item current">
                                            <a href="wood">Wood</a>
                                        </li> 
                                        <li class="breadcrumb-item current gray">
                                            <!--<a href="index.php?page=compaore">Product Comparison</a>-->
                                            <a href="#">Wood Comparison</a>
                                        </li>
                                    </ul>
                                </li> 
                                <!--/.breadcrumb-nav-holder--> 
                            </ul>
                        </nav>
                    </div>
                    <!--/.container--> 
                </div> 
                <!--/#top-mega-nav--> 
                <!-- ========================================= BREADCRUMB : END ========================================= --></div>     <div class="main-content" id="main-content">
                <div class="container">
                    <div class="inner-xs">
                        <div class="page-header">
                            <h2 class="page-title">
                                Wood Comparison            
                            </h2>
                        </div>
                    </div><!-- /.section-page-title -->
            
                    <div class="table-responsive inner-bottom-xs inner-top-xs">
            
                        
                        
                        
                        
                        <table class="table table-bordered table-striped compare-list">
                            <thead>
                                <tr>
                                    <td>&nbsp;</td>
                            
                                    <c:forEach items="${listWood}" var="item1">
                                        
                                        <td class="text-center">
                            
                                            <div class="image-wrap">
                                                
                                                <a data-product_id="39" href="/compare-wood-del-${item1.id}" class="remove-link">
                                                    <i class="fa fa-times-circle">  </i>
                                                </a>
                                    
                                                <!--<a href="vmc${item1.id}" > <img width="220" height="154" alt="Iconia W700" class="attachment-yith-woocompare-image" src="../resources/assets/images/products/${item1.photo1}">   </a>-->                     
                                                <a href="/wood-${item1.url}" > <img width="220" height="154" alt="Iconia W700" class="attachment-yith-woocompare-image" src="../resources/assets/images/products/${item1.photo1}">   </a>                     
                                    
                                            </div>
                                            
                                            <p><a href="/wood-${item1.url}" > <strong>${item1.name}</strong> </a> </p>
                                        </td>
                                    </c:forEach> 
                                </tr>
                            </thead>
                    
                            <tbody>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodcompare.description" text="Description"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <p> ${wood.getDescription(pageContext.response.locale)}</p>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodall.type" text="Type"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <p> ${wood.type} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                        
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodall.length" text="Length"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39" style=${wood.preferences.length ? "background:#FEC" : ""} >
                                            <p> ${wood.length} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                        
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodall.width" text="Width"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39" style=${wood.preferences.width ? "background:#FEC" : ""}>
                                            <p> ${wood.width} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                        
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodall.thickness" text="Thickness"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39" style=${wood.preferences.thickness ? "background:#FEC" : ""}>
                                            <p> ${wood.thickness} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.grade" text="Grade"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <c:if test="${wood.rawPlywood}">
                                                <p> ${wood.grade} </p>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.sanded" text="Sanded"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <c:if test="${wood.rawPlywood}">
                                                <p> ${wood.isSanded ? '+' : '-'} </p>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.water_resistance" text="Water resistance"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <c:if test="${wood.rawPlywood}">
                                                <p> ${wood.isWaterResistance ? 'FK' : '-'} </p>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.laminated_color" text="Laminated color"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <c:if test="${wood.laminatedParticleBoard}">
                                                <p> ${wood.laminatedColor} </p>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.num_boards_in_package" text="Quantity boards in package"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <p> ${wood.numBoardsInPackage} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <tr class="comparison-item description">
                                    <th><spring:message code="woodone.num_packages_in_vehicle" text="Quantity packages in vehicle"/></th>
                                        <c:forEach items="${listWood}" var="wood">
                                        <td class="comparison-item-cell odd product_39">
                                            <p> ${wood.numPackagesInVehicle} </p>
                                        </td>
                                    </c:forEach>
                                </tr>
                                
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <tr class="comparison-item description">
                                        <th>Price (EXW)</th>
                                        <c:forEach items="${listWood}" var="wood">
                                            <td class="comparison-item-cell odd product_39">
                                                <p> ${wood.price} </p>
                                            </td>
                                        </c:forEach>
                                    </tr>  
                                </sec:authorize>
                                    
                            </tbody>
                        </table>
                
                
                
                    </div><!-- /.table-responsive -->
                </div><!-- /.container -->  
            </div>   
    
    
            <!-- ============================================================= FOOTER ============================================================= -->
    
            <%@include file="/WEB-INF/pages/in/footer.jsp" %>
    
            <!-- ============================================================= FOOTER : END ============================================================= -->   
        </div>
        
        <%@include file="/WEB-INF/pages/in/config_open.jsp" %>
    
    
        <!-- JavaScripts placed at the end of the document so the pages load faster -->
        <script src="../resources/assets/js/jquery-1.10.2.min.js"></script>
        <script src="../resources/assets/js/jquery-migrate-1.2.1.js"></script>
        <script src="../resources/assets/js/bootstrap.min.js"></script>
        <script src="http://maps.google.com/maps/api/js?sensor=false&amp;language=en"></script>
        <script src="../resources/assets/js/gmap3.min.js"></script>
        <script src="../resources/assets/js/bootstrap-hover-dropdown.min.js"></script>
        <script src="../resources/assets/js/owl.carousel.min.js"></script>
        <script src="../resources/assets/js/css_browser_selector.min.js"></script>
        <script src="../resources/assets/js/echo.min.js"></script>
        <script src="../resources/assets/js/jquery.easing-1.3.min.js"></script>
        <script src="../resources/assets/js/bootstrap-slider.min.js"></script>
        <script src="../resources/assets/js/jquery.raty.min.js"></script>
        <script src="../resources/assets/js/jquery.prettyPhoto.min.js"></script>
        <script src="../resources/assets/js/jquery.customSelect.min.js"></script>
        <script src="../resources/assets/js/wow.min.js"></script>
        <script src="../resources/assets/js/scripts.js"></script>
        
        <!-- For demo purposes â can be removed on production -->
        
        <script src="../resources/assets/switchstylesheet/switchstylesheet.js"></script>
     
        <script>
            $(document).ready(function(){ 
                $(".changecolor").switchstylesheet( { seperator:"color"} );
                $('.show-theme-options').click(function(){
                    $(this).parent().toggleClass('open');
                    return false;
                });
            });
        
            $(window).bind("load", function() {
                $('.show-theme-options').delay(2000).trigger('click');
            });
        </script>
        <!-- For demo purposes â can be removed on production : End -->
        
        <script src="http://w.sharethis.com/button/buttons.js"></script>
        
    </body>
</html>