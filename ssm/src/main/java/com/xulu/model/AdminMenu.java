package com.xulu.model;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 13:45
 * m_id VARCHAR2(32) PRIMARY KEY NOT NULL,
m_path VARCHAR2(64) DEFAULT NULL,
m_name VARCHAR2(64) DEFAULT NULL,
m_name_zh VARCHAR2(64) DEFAULT NULL,
m_icon_cls VARCHAR2(64) DEFAULT NULL,
m_component VARCHAR2(64) DEFAULT NULL,
m_parent_id VARCHAR2(32) DEFAULT NULL
 */
public class AdminMenu {
    private String m_id;
    private String m_path;
    private String m_name;
    private String m_name_zh;
    private String m_icon_cls;
    private String m_component;
    private String m_parent_id;

    private List<AdminMenu> children;

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_path() {
        return m_path;
    }

    public void setM_path(String m_path) {
        this.m_path = m_path;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_name_zh() {
        return m_name_zh;
    }

    public void setM_name_zh(String m_name_zh) {
        this.m_name_zh = m_name_zh;
    }

    public String getM_icon_cls() {
        return m_icon_cls;
    }

    public void setM_icon_cls(String m_icon_cls) {
        this.m_icon_cls = m_icon_cls;
    }

    public String getM_component() {
        return m_component;
    }

    public void setM_component(String m_component) {
        this.m_component = m_component;
    }

    public String getM_parent_id() {
        return m_parent_id;
    }

    public void setM_parent_id(String m_parent_id) {
        this.m_parent_id = m_parent_id;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }
}
