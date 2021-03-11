package top.b0x0.demo.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.b0x0.demo.domain.LoginEntity;
import top.b0x0.demo.domain.ResponseEntry;
import top.b0x0.demo.service.IRegisterService;
import top.b0x0.demo.util.StringUtil;
import top.b0x0.demo.util.UUIDUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * 注册Action
 *
 * @author TANG
 */
@RestController
public class RegisterAction {

    @Autowired
    IRegisterService registerService;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("/register")
    public ResponseEntry register(@RequestParam("phone") String phone,
                                  @RequestParam("password") String password) {
        ResponseEntry entry = new ResponseEntry();
        LoginEntity loginEntity = new LoginEntity();
        boolean isEmp = false;
        if (StringUtil.isEmpty(phone)) {
            isEmp = true;
            entry.setMessage("手机不能为空");
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        }
        if (StringUtil.isEmpty(password)) {
            isEmp = true;
            entry.setMessage("密码不能为空");
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        }
        if (!isEmp) {
            loginEntity.setPhone(phone);
            loginEntity.setPassword(password);
            loginEntity.setToken(UUIDUtil.getUUID());
            int result = registerService.register(loginEntity);
            if (result == -1) {
                entry.setMessage("注册失败");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            }
        }

        return entry;
    }


    /**
     * 批量注册
     *
     * @param body [
     *             {
     *             "id": null,
     *             "phone": "1111",
     *             "password": "1111",
     *             "token": "1111"
     *             },
     *             {
     *             "id": null,
     *             "phone": "1111",
     *             "password": "1111",
     *             "token": "1111"
     *             },
     *             {
     *             "id": null,
     *             "phone": "1111",
     *             "password": "1111",
     *             "token": "1111"
     *             },
     *             {
     *             "id": null,
     *             "phone": "1111",
     *             "password": "1111",
     *             "token": "1111"
     *             }
     *             ]
     * @return /
     */
    @PostMapping("/registerBatch")
    public ResponseEntry registerBatch(@RequestBody String body) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(body)) {
            entry.setMessage("参数不能为空");
            entry.setCode(ResponseEntry.DATA_EXCEPTION);
        } else {
            ArrayList<LoginEntity> list = null;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LoginEntity>>() {
            }.getType();
            list = gson.fromJson(body, type);
            if (null == list || list.size() <= 0) {
                entry.setMessage("数据异常");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            } else {
                int result = registerService.registerBatch(list);
                if (result == -1) {
                    entry.setMessage("注册失败");
                    entry.setCode(ResponseEntry.DATA_EXCEPTION);
                }
            }

        }
        return entry;
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<LoginEntity> list = new ArrayList<>();
//        LoginEntity loginEntity = new LoginEntity();
//        loginEntity.setPhone("1111");
//        loginEntity.setPassword("1111");
//        loginEntity.setToken("1111");
//        list.add(loginEntity);
//        list.add(loginEntity);
//        list.add(loginEntity);
//        list.add(loginEntity);
//        String asString = mapper.writeValueAsString(list);
//        System.out.println("asString = " + asString);
//    }

    /**
     * 批量更新
     *
     * @param body [
     *             {
     *             "phone": "1111",
     *             "password": "123",
     *             "token": "1111"
     *             },
     *             {
     *             "phone": "2222",
     *             "password": "123",
     *             "token": "2222"
     *             },
     *             {
     *             "phone": "3333",
     *             "password": "123",
     *             "token": "3333"
     *             }
     *             ]
     * @return /
     */
    @PostMapping("/updateBatch")
    public ResponseEntry updateBatch(@RequestBody String body) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(body)) {
            entry.setMessage("参数不能为空");
            entry.setCode(ResponseEntry.DATA_EXCEPTION);
        } else {
            ArrayList<LoginEntity> list = null;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LoginEntity>>() {
            }.getType();
            list = gson.fromJson(body, type);
            if (null == list || list.size() <= 0) {
                entry.setMessage("数据异常");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            } else {
                int result = registerService.updateBatch(list);
                if (result <= 0) {
                    entry.setMessage("注册失败");
                    entry.setCode(ResponseEntry.DATA_EXCEPTION);
                }
            }

        }
        return entry;
    }

    /**
     * 批量更新
     *
     * @param body [
     *             {
     *             "phone": "1111",
     *             "password": "2222",
     *             "token": ""
     *             },
     *             {
     *             "phone": "2222",
     *             "password": "123",
     *             "token": ""
     *             },
     *             {
     *             "phone": "3333",
     *             "password": "666",
     *             "token": "666"
     *             }
     *             ]
     * @return /
     */
    @PostMapping("/updateBatch2")
    public ResponseEntry updateBatch2(@RequestBody String body) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(body)) {
            entry.setMessage("参数不能为空");
            entry.setCode(ResponseEntry.DATA_EXCEPTION);
        } else {
            ArrayList<LoginEntity> list = null;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LoginEntity>>() {
            }.getType();
            list = gson.fromJson(body, type);
            if (null == list || list.size() <= 0) {
                entry.setMessage("数据异常");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            } else {
                int result = registerService.updateBatch2(list);
                if (result <= 0) {
                    entry.setMessage("注册失败");
                    entry.setCode(ResponseEntry.DATA_EXCEPTION);
                }
            }

        }
        return entry;
    }

    /**
     * 批量删除
     *
     * @param reequestBody /
     * @return /
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public ResponseEntry deleteBatch(@RequestBody String reequestBody) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(reequestBody)) {
            entry.setMessage("参数不能为空");
            entry.setCode(ResponseEntry.DATA_EXCEPTION);
        } else {
            ArrayList<LoginEntity> list = null;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<LoginEntity>>() {
            }.getType();
            list = gson.fromJson(reequestBody, type);
            if (null == list || list.size() <= 0) {
                entry.setMessage("数据异常");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            } else {
                int result = registerService.deleteBatch(list);
                if (result <= 0) {
                    entry.setMessage("注册失败");
                    entry.setCode(ResponseEntry.DATA_EXCEPTION);
                }
            }

        }
        return entry;
    }


}
