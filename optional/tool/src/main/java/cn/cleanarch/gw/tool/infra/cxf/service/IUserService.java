package cn.cleanarch.gw.tool.infra.cxf.service;

import cn.cleanarch.gw.tool.infra.cxf.dataobject.User;

import javax.ws.rs.*;
import java.util.List;

@Path("/userService")
@Produces("*/*")
public interface IUserService {

    @POST
    @Path("/user") // 设置二级访问路径
    @Consumes({"application/xml", "application/json"})
        // 设置接收参数的格式
    void saveUser(User user);

    @PUT
    @Path("/user")
    @Consumes({"application/xml", "application/json"})
    void updateUser(User user);

    @GET
    @Path("/user")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/json"})
        // 指定返回数据格式
    List<User> findAll();

    @GET
    @Path("/user/{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    User findUserById(@PathParam("id") Integer id);  // 参数指定了接收数据的名字

    @DELETE
    @Path("/user/{id}")
    @Consumes({"application/xml", "application/json"})
    void deleteUserById(@PathParam("id") Integer id);
}