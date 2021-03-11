package top.b0x0.demo.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.b0x0.demo.domain.LoginEntity;
import top.b0x0.demo.domain.ResponseEntry;
import top.b0x0.demo.service.ILoginService;
import top.b0x0.demo.util.StringUtil;

import java.util.List;


/**
 * 登录测试
 *
 * @author TANG
 */
@RestController
public class LoginAction {

    @Autowired
    private ILoginService loginService;

    /**
     *
     * @param phone    /
     * @param password /
     * @param token    /
     * @return /
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntry onLogin(@RequestParam("phone") String phone,
                                 @RequestParam("password") String password, @RequestParam("token") String token) {
        ResponseEntry entry = new ResponseEntry();
        LoginEntity loginEntity;
        boolean isEmp = false;
        if (StringUtil.isEmpty(phone)) {
            isEmp = true;
            entry.setMessage("手机不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            isEmp = true;
            entry.setMessage("密码不能为空");
        }
        if (StringUtil.isEmpty(token)) {
            isEmp = true;
            entry.setMessage("token不能为空");
        }
        if (!isEmp) {
            loginEntity = loginService.select(token);
            if (null == loginEntity) {
                entry.setMessage("用户不存在");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            } else {
                if (!loginEntity.getPassword().equals(password) || !loginEntity.getPhone().equals(phone)) {
                    entry.setMessage("手机号或密码错误");
                    entry.setCode(ResponseEntry.DATA_EXCEPTION);
                }
            }
        } else {
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        }

        return entry;
    }

    /**
     * 查询
     *
     * @param token 用户唯一标识码
     * @return /
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public ResponseEntry select(@RequestParam("token") String token) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(token)) {
            entry.setMessage("token不能为空");
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        } else {
            LoginEntity loginEntity = loginService.select(token);
            if (null != loginEntity) {
//				String body = new Gson().toJson(loginEntity);
                entry.setBody(loginEntity);
            }
        }
        return entry;
    }

    /**
     * 更新密码
     *
     * @return /
     */
    @RequestMapping("/update/pwd")
    public ResponseEntry updatePwd(@RequestParam("password") String password,
                                   @RequestParam("token") String token) {
        ResponseEntry entry = new ResponseEntry();
        LoginEntity loginEntity = new LoginEntity();
        boolean isEmp = false;
        if (StringUtil.isEmpty(password)) {
            isEmp = true;
            entry.setMessage("密码不能为空");
        }
        if (StringUtil.isEmpty(token)) {
            isEmp = true;
            entry.setMessage("token不能为空");
        }
        if (!isEmp) {
            loginEntity.setToken(token);
            loginEntity.setPassword(password);
            loginService.update(loginEntity);
        } else {
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        }
        return entry;
    }


    /**
     * 删除
     *
     * @param token 用户唯一标识码
     * @return /
     */
    @PostMapping(value = "/delete")
    public ResponseEntry delete(@RequestParam("token") String token) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(token)) {
            entry.setMessage("token不能为空");
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        } else {
            int result = loginService.delete(token);
            if (result == 0) {
                entry.setMessage("删除失败");
                entry.setCode(ResponseEntry.PAREM_EXCEPTION);
            }
        }
        return entry;
    }

    /**
     * 获取数据列表
     *
     * @return /
     */
    @PostMapping(value = "/getlist/users")
    public ResponseEntry getAllDataList(@RequestParam("token") String token) {
        ResponseEntry entry = new ResponseEntry();
        if (StringUtil.isEmpty(token)) {
            entry.setMessage("token不能为空");
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
        } else {
            LoginEntity loginEntity;
            loginEntity = loginService.select(token);
            if (null == loginEntity) {
                entry.setMessage("用户不存在");
                entry.setCode(ResponseEntry.DATA_EXCEPTION);
            }
            List<LoginEntity> list = loginService.getAll();
            if (null != list) {
//    			String body = new Gson().toJson(list);
                entry.setBody(list);
            }
        }
        return entry;
    }


}
