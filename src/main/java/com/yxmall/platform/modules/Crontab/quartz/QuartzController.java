package com.yxmall.platform.modules.Crontab.quartz;

import com.yxmall.platform.common.utils.QuartzJobManager;
import com.yxmall.platform.modules.system.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 17/01/2019.
 */
@RestController
@RequestMapping("task")
public class QuartzController {
    @Autowired
    QuartzService quartzService;
    /**
     * 添加定时任务1
     * @param request
     * @throws Exception
     */
    @PostMapping("test1")
    public void test1(HttpServletRequest request) throws Exception {
        QuartzJobManager.getInstance().addJob(TestQuartz1.class,"job1","job1", "*/1 * * * * ?","任务1");
    }
    /**
     * 添加定时任务2
     * @param request
     * @throws Exception
     */
    @PostMapping("test2")
    public void test2(HttpServletRequest request) throws Exception {
        QuartzJobManager.getInstance().addJob(TestQuartz2.class,"job2","job2", "*/10 * * * * ?","任务2");
    }


    /**
     * 暂停指定任务
     * @param request
     * @throws Exception
     */
    @PostMapping("stop")
    public void stopJob(HttpServletRequest request) throws Exception {
        /*String name = PrimaryKeyUtil.nextId();*/
        QuartzJobManager.getInstance().pauseJob("job1","job1");
    }

    /**
     * 恢复指定任务
     * @param request
     * @throws Exception
     */
    @PostMapping("resume")
    public void startJob(HttpServletRequest request) throws Exception {
        /*String name = PrimaryKeyUtil.nextId();*/
        QuartzJobManager.getInstance().resumeJob("job1","job1");
    }

    /**
     * 更新指定任务(修改任务执行时间为30秒，原来是每隔1秒执行任务)
     * @param request
     * @throws Exception
     */
    @PostMapping("update")
    public void updateJob(HttpServletRequest request) throws Exception {
        /*String name = PrimaryKeyUtil.nextId();*/
        QuartzJobManager.getInstance().updateJob("job1","job1","*/30 * * * * ?");
    }

    /**
     * 删除指定任务
     * @param request
     * @throws Exception
     */
    @PostMapping("delete")
    public void deleteJob(HttpServletRequest request) throws Exception {
        /*String name = PrimaryKeyUtil.nextId();*/
        QuartzJobManager.getInstance().deleteJob("job1","job1");
    }
    /**
     * 查看所有任务列表
     */
    /**
     * 更新指定任务(修改任务执行时间为30秒，原来是每隔1秒执行任务)
     * @param request
     * @throws Exception
     */
    @PostMapping("select")
    public void selectJob(HttpServletRequest request) throws Exception {
        /*String name = PrimaryKeyUtil.nextId();*/
        List<Map<String, Object>> list= QuartzJobManager.getInstance().getAllJob();
        for (Map<String, Object> m : list)
        {
            for (String k : m.keySet())
            {
                System.out.println("===========" + m.get(k));
            }

        }
    }


}
