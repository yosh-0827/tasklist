package jp.gihyo.project.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    private final TaskListDao dao;

    //初期化用コンストラクタ
    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }
    @RequestMapping("/hello")       //"~/hello"というパスに対するエンドポイントとして指定
    //@ResponseBody                   //戻り値としてビューを表すオブジェクトを返す
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";             //ビューを構成するHTMLはテンプレートエンジンが作成してくるので対応するビュー名を拡張子除いて返す
    }

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();
    //タスク一覧表示用エンドポイント(home.htmlに対応)
    @GetMapping("/list")
    String listItems(Model model) {
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("taskList", taskItems);   //キー：taskList, バリュー：taskItem
        return "home";
    }

    //タスク追加のエンドポイント
    @GetMapping("/add")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        dao.add(item);

        return "redirect:/list";
    }
}
