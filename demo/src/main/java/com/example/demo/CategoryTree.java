package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CategoryTree {
    private Map<Integer, Category> categories = new HashMap<>();
    private Map<Integer, List<Integer>> relationships = new HashMap<>();

    // 카테고리 추가
    public void addCategory(int id, String name) {
        categories.put(id, new Category(id, name));
    }

    // 부모-자식 관계 등록
    public void addRelationship(int parentId, int childId) {
        relationships.putIfAbsent(parentId, new ArrayList<>());
        relationships.get(parentId).add(childId);
    }

    // relationships 맵을 바탕으로 실제 트리 구축
    public void buildTree() {
        for (Map.Entry<Integer, List<Integer>> entry : relationships.entrySet()) {
            Category parent = categories.get(entry.getKey());
            for (Integer childId : entry.getValue()) {
                Category child = categories.get(childId);
                parent.addSubCategory(child);
            }
        }
    }

    // ID로 카테고리 찾기
    public Category getCategoryById(int id) {
        return categories.get(id);
    }

    // 이름으로 카테고리 찾기
    public Category getCategoryByName(String name) {
        for (Category category : categories.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    // 특정 ID의 카테고리를 JSON 형태로 변환
    public String toJson(int categoryId) {
        Category category = categories.get(categoryId);
        if (category != null) {
            return category.toJson();
        }
        return null;
    }
}
