package cn.ghaien.machinedemo.controller;

import cn.ghaien.machinedemo.entity.input.MachineInfoQuery;
import cn.ghaien.machinedemo.entity.pojo.MachineInfo;
import cn.ghaien.machinedemo.service.MachineInfoService;
import cn.ghaien.machinedemo.util.commons.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author guo.haien
 * @Date 2018/7/3
 */
@Controller
public class HtmlController {

    private static String URL_PREFIX = "/web/html/";

    private static String URL_SUFFIX = ".html";

    @Autowired
    private MachineInfoService machineInfoService;

    /**
     * 访问首页页面
     *
     * @return
     */
    @RequestMapping(value = "/index/{machineUseNo}")
    public void index(@PathVariable(value = "machineUseNo") String machineUseNo, HttpServletResponse response) {
        try {
            MachineInfoQuery query = new MachineInfoQuery();
            query.setMachineUseNo(machineUseNo);
            List<MachineInfo> machineInfoList = machineInfoService.queryList(query).getData();
            if (machineInfoList.size() == 0) {
                throw new Exception("设备访问编号不存在");
            }
            response.sendRedirect(URL_PREFIX + "index" + URL_SUFFIX + "?devId=" + machineInfoList.get(0).getMachineNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 访问所有防区报警延时设置页面
     *
     * @return
     */
    @RequestMapping(value = "/allZoneAlarm")
    public String allZoneAlarm() {
        return URL_PREFIX + "allZoneAlarm" + URL_SUFFIX;
    }

    /**
     * 访问所有防区高压值设置页面
     *
     * @return
     */
    @RequestMapping(value = "/allZoneHigh")
    public String allZoneHigh() {
        return URL_PREFIX + "allZoneHigh" + URL_SUFFIX;
    }

    /**
     * 访问所有防区低压值设置页面
     *
     * @return
     */
    @RequestMapping(value = "/allZoneLow")
    public String allZoneLow() {
        return URL_PREFIX + "allZoneLow" + URL_SUFFIX;
    }

    /**
     * 访问所有防区防剪灵敏度设置页面
     *
     * @return
     */
    @RequestMapping(value = "/allZoneShear")
    public String allZoneShear() {
        return URL_PREFIX + "allZoneShear" + URL_SUFFIX;
    }

    /**
     * 访问所有防区触网灵敏度设置页面
     *
     * @return
     */
    @RequestMapping(value = "/allZoneTouch")
    public String allZoneTouch() {
        return URL_PREFIX + "allZoneTouch" + URL_SUFFIX;
    }

    /**
     * 访问报警历史纪录页面
     *
     * @return
     */
    @RequestMapping(value = "/armHistory")
    public String armHistory() {
        return URL_PREFIX + "armHistory" + URL_SUFFIX;
    }

    /**
     * 访问通信检测设定页面
     *
     * @return
     */
    @RequestMapping(value = "/communicationCheck")
    public String communicationCheck() {
        return URL_PREFIX + "communicationCheck" + URL_SUFFIX;
    }

    /**
     * 访问设备列表页页面
     *
     * @return
     */
    @RequestMapping(value = "/machineList")
    public String machineList() {
        return URL_PREFIX + "machineList" + URL_SUFFIX;
    }

    /**
     * 访问更多操作页面
     *
     * @return
     */
    @RequestMapping(value = "/moreOperate")
    public String moreOperate() {
        return URL_PREFIX + "moreOperate" + URL_SUFFIX;
    }

    /**
     * 访问单个防区报警延时设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneAlarm")
    public String singleZoneAlarm() {
        return URL_PREFIX + "singleZoneAlarm" + URL_SUFFIX;
    }

    /**
     * 访问单个防区布防设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneArming")
    public String singleZoneArming() {
        return URL_PREFIX + "singleZoneArming" + URL_SUFFIX;
    }

    /**
     * 访问单个防区高压值输出设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneHigh")
    public String singleZoneHigh() {
        return URL_PREFIX + "singleZoneHigh" + URL_SUFFIX;
    }

    /**
     * 访问单个防区低压值输出设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneLow")
    public String singleZoneLow() {
        return URL_PREFIX + "singleZoneLow" + URL_SUFFIX;
    }

    /**
     * 访问单个防区防剪灵敏度设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneShear")
    public String singleZoneShear() {
        return URL_PREFIX + "singleZoneShear" + URL_SUFFIX;
    }

    /**
     * 访问单个防区触网灵敏度设定页面
     *
     * @return
     */
    @RequestMapping(value = "/singleZoneTouch")
    public String singleZoneTouch() {
        return URL_PREFIX + "singleZoneTouch" + URL_SUFFIX;
    }

    /**
     * 访问系统时钟设定页面
     *
     * @return
     */
    @RequestMapping(value = "/systemTime")
    public String systemTime() {
        return URL_PREFIX + "systemTime" + URL_SUFFIX;
    }

    /**
     * 访问定时分组设定页面
     *
     * @return
     */
    @RequestMapping(value = "/timingGroup")
    public String timingGroup() {
        return URL_PREFIX + "timingGroup" + URL_SUFFIX;
    }
}
