package top.b0x0.demo.action;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/test")
public class ControllerAction {

    @RequestMapping("/showView/{var1}/{var2}")
    public ModelAndView showView(@PathVariable("var1") String variable1, @PathVariable("var2") int variable2) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewName");
        modelAndView.addObject(" 需要放到 model 中的属性名称 ", " 对应的属性值，它是一个对象 ");
        return modelAndView;
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    String name(@RequestParam(value = "name") String name) {
        return name;
    }

    @RequestMapping("/hell")
    String home() {
        return "Hello World!";
    }


}
