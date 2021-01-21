<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<script type="text/javascript">
        $(function () {
            $("input[type=button]").click(function () {
               window.location.href="pay/payForGoods?name="+$("input[name='name']").val()+"&phoneNumber="+$("input[name='phoneNumber']").val()+"&price="+100;
            })
        })
 </script>


</head>
<body>
<form name="alipayment" action= "${pageContext.request.contextPath}/pay/payForGoods.do" method=post
            target="_blank">
            <div id="body1" class="show" name="divcontent">
                <dl class="content">
                    <dt>商户订单号 ：</dt>
                    <dd>
                        <input id="WIDout_trade_no" name="WIDout_trade_no" />
                    </dd>
                    <hr class="one_line">
                    <dt>订单名称 ：</dt>
                    <dd>
                        <input id="WIDsubject" name="WIDsubject" />
                    </dd>
                    <hr class="one_line">
                    <dt>付款金额 ：</dt>
                    <dd>
                        <input id="WIDtotal_amount" name="WIDtotal_amount" />
                    </dd>
                    <hr class="one_line">
                    <dt>商品描述：</dt>
                    <dd>
                        <input id="WIDbody" name="WIDbody" />
                    </dd>
                    <hr class="one_line">
                    <dt></dt>
                    <dd id="btn-dd">
                        <span class="new-btn-login-sp">
                            <button class="new-btn-login" type="submit"
                                style="text-align: center;">付 款</button>
                        </span> <span class="note-help">如果您点击“付款”按钮，即表示您同意该次的执行操作。</span>
                    </dd>
                </dl>
            </div>
        </form>
<!--这里的target为_blank是新打开一个窗口-->
</body>
</html>


