package com.zhst.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 *
 * @param <T>
 */
public class Tree<T> {
    /**
     * 节点ID
     */
    private Integer id;
    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 父节点显示文本
     */
    private String parentText;

    /**
     * 显示节点文本
     */
    private String text;

    /**
     * 节点本身
     */
    private T node;

    /**
     * 是否有孩子节点
     */
    private boolean hasChildren = false;

    /**
     * 节点的子节点
     */
    private List<Tree<T>> children = new ArrayList<>();


    /**
     * 图标
     */
    private String icon;

    /**
     * 连接地址
     */
    private String url;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }


    public void setNode(T node) {
        this.node = node;
    }

    public T getNode() {
        return node;
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Tree(Integer id, String text, T node,
                List<Tree<T>> children, Integer parentId,
                String parentText, boolean hasChildren,
                String icon, String url) {
        super();
        this.id = id;
        this.text = text;
        this.node = node;
        this.children = children;
        this.parentId = parentId;
        this.parentText = parentText;
        this.hasChildren = hasChildren;
        this.icon = icon;
        this.url = url;
    }

    public Tree() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}