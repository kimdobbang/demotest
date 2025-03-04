package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Category> subCategories;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.subCategories = new ArrayList<>();
    }

    public void addSubCategory(Category category) {
        // 중복 체크 없이 추가
        subCategories.add(category);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    // 객체와 모든 하위 카테고리를 JSON으로 변환
    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 해당 Category(자기 자신) 또는 자식, 손자 등 모든 하위 트리에 target이 포함되면 true 반환
    public boolean containsDescendant(Category target) {
        // 자기 자신이 target이면 true
        if (this == target) {
            return true;
        }

        // 자식들을 재귀적으로 탐색
        for (Category child : subCategories) {
            if (child.containsDescendant(target)) {
                return true;
            }
        }
        return false;
    }
}
