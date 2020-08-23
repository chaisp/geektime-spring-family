package com.example.swagger.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation(value = "222")
@Api(tags = {"第二个控制类,负责XXXXXX接口"})
@RequestMapping("/Second")
@ApiImplicitParams({
        @ApiImplicitParam(name="uuid",value="用户名",dataType="string", paramType = "query",example="xingguo"),
        @ApiImplicitParam(name="DeviceUuid",value="设备Uuid",dataType="json", paramType = "query")})
public class SecondController {
    @ApiOperation(value="输出Hello",notes = "{public Docket docket(Environment enviroment){\n" +
            "    //设置要显示的Swagger环境\n" +
            "    Profiles profiles = Profiles.of(\"dev\",\"test\");\n" +
            "    //通过acceptProfile判断是否处在自己设定的环境当中\n" +
            "    boolean flag = enviroment.acceptsProfiles(profiles);\n" +
            "    通过flag判断是不是在dev或者test环境中\n" +
            "}}")
    @GetMapping("/hello")
    public  String helloController(){
        return "hello Swagger";
    }
}
