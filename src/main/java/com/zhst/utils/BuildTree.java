package com.zhst.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建树
 */
public class BuildTree {

    /**
     * 构建
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T> List<Tree<T>> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        for (Tree<T> children : nodes) {
            Integer pid = children.getParentId();
            if (pid == null) {
                topNodes.add(children);
                continue;
            }
            for (Tree<T> parent : nodes) {
                Integer id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    parent.setHasChildren(true);
                }
            }

        }
        if (CollectionUtils.isEmpty(topNodes)) {
            return null;
        }
        return topNodes;
    }
}
