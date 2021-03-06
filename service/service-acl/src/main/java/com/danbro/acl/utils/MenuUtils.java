package com.danbro.acl.utils;

import com.alibaba.fastjson.JSONObject;
import com.danbro.acl.controller.vo.TreeNodePermissionVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MenuUtils
 * @Description TODO
 * @Date 2021/1/14 12:50
 * @Author Danrbo
 */
public class MenuUtils {

    /**
     * 构建菜单
     *
     * @param treeNodes
     * @return
     */
    public static List<JSONObject> bulid(List<TreeNodePermissionVo> treeNodes) {
        List<JSONObject> menuList = new ArrayList<>();
        if (treeNodes.size() == 1) {
            TreeNodePermissionVo topNode = treeNodes.get(0);
            //左侧一级菜单
            List<TreeNodePermissionVo> oneMenuList = topNode.getChildren();
            for (TreeNodePermissionVo one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_" + one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<TreeNodePermissionVo> twoMenuList = one.getChildren();
                for (TreeNodePermissionVo two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_" + two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<TreeNodePermissionVo> threeMenuList = two.getChildren();
                    for (TreeNodePermissionVo three : threeMenuList) {
                        if (StringUtils.isEmpty(three.getPath())) continue;

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_" + three.getId());
                        threeMenu.put("hidden", true);
                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menuList.add(oneMenu);
            }
        }
        return menuList;
    }
}