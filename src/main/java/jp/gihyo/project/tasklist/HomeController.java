package jp.gihyo.project.tasklist;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class HomeController {
    @RequestMapping("/hello")       //"~/hello"というパスに対するエンドポイントとして指定
    //@ResponseBody                   //戻り値としてビューを表すオブジェクトを返す
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";             //ビューを構成するHTMLはテンプレートエンジンが作成してくるので対応するビュー名を拡張子除いて返す
    }
}
