## 自定义ArgumentResolver

### 项目场景:

一个TTS系统,为上游系统提供统一的填单,下单,查询订单等接口服务.这就要求TTS系统对外暴漏统一的接口参数列表.
下游接入多个代理商,而且针对每个代理商的填单,下单等接口参数名各不相同.
TTS系统需要根据请求参数判断代理商类型,然后将统一接口中的参数传递给针对该代理商的请求类型.

以下单接口为例:
对外的接口形式为`SubmitResult submit(SubmitRequest request);`
封装一个统一的Request类型`SubmitRequest`,其中包含了所有代理商都需要的参数,只是参数名称与各个代理商定义的有所不同,假如其中有个参数`orderId`.
封装一个统一的Result类型`SubmitResult`,其中包含了上游系统需要的所有代理商返回的参数,统一命名,假如其中有个参数`orderId`.

这时要向SupplierA下单,我们肯定要封装一个对应的下单参数`SupplierASubmitRequest`,而SupplierA的接口文档中订单号叫`orderNo`,那我们需要将`SubmitRequest`中的`orderId`赋值给`orderNo`.
有了请求参数,就调用SupplierA的接口下单,返回结果`SupplierASubmitResult`,里面的订单号还是叫`orderNo`.
不能将`SupplierASubmitResult`类型直接返回给上游系统,还是需要统一参数,将`orderNo`赋值给`SubmitResult`的`orderId`,最后再返回.

通过这样的参数统一,对上游系统屏蔽了下游代理商的参数异构性.

### 简单方案
对外暴露统一的下单接口Controller,接收`SubmitRequest`参数,返回`SubmitResult`结果:
```
    @RequestMapping(value = "submit")
    @ResponseBody
    public SubmitResult submit(SubmitRequest submitRequest) {
        SubmitResult submit = service.submit(submitRequest);
        logger.info("result:" + submit);
        return submit;
    }
```
内部调用TTSService服务类,根据请求参数中的supplierId区分Supplier.调用不同的SupplierDao进行处理.
处理前需要将`SubmitRequest`转化为具体的SupplierSubmitRequest`,调用后,需要将返回的`SupplierSubmitResult`转化为统一的`SubmitResult`.

### 待优化点

1. `TTSService`中需要根据supplierId进行业务分派,每次都需要参数类型转化.
2. `SupplierDao`中都需要http调用,可以抽象出来.


