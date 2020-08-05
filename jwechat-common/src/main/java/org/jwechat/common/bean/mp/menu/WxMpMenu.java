package org.jwechat.common.bean.mp.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



/**
 * @Title Menu
 * @Description 自定义菜单
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxMpMenu {

    /**
     *  一级菜单最多三个
     */
    @JsonProperty("button")
    private List<WxMenuButton> buttons = new ArrayList<>();

    public void setMenuButton(List<WxMenuButton> subButtons) {
        if (null == subButtons || subButtons.size() > 3) {
            throw new IllegalArgumentException("一级菜单最多只有3个");
        }
        this.buttons = subButtons;
    }

//    public void addMenuButton(SubButton mb) {
//        this.menuButton.add(mb);
//        if (this.menuButton.size() > 3) {
//            throw new IllegalArgumentException("一级菜单最多只有3个");
//        }
//    }

}
