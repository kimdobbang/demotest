package com.example.demo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTreeTest {

    @Test
    void testAddCategory() {
        CategoryTree tree = new CategoryTree();
        tree.addCategory(1, "남자");
        Category category = tree.getCategoryById(1);
        assertNotNull(category);
        assertEquals("남자", category.getName());
    }

    @Test
    void testAddRelationship() {
        CategoryTree tree = new CategoryTree();
        tree.addCategory(1, "남자");
        tree.addCategory(2, "방탄소년단");
        tree.addCategory(3, "공지사항");
        tree.addRelationship(1, 2);  // 남자 -> 방탄소년단
        tree.addRelationship(2, 3);  // 방탄소년단 -> 공지사항
        tree.buildTree();

        Category parent = tree.getCategoryById(1);  // 남자
        Category child = tree.getCategoryById(2);   // 방탄소년단
        assertTrue(parent.getSubCategories().contains(child));
    }

    @Test
    void testToJson() {
        CategoryTree tree = new CategoryTree();
        tree.addCategory(1, "남자");
        tree.addCategory(2, "방탄소년단");
        tree.addCategory(3, "공지사항");
        tree.addRelationship(1, 2);
        tree.addRelationship(2, 3);
        tree.buildTree();

        String json = tree.toJson(1);
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"남자\""));
        assertTrue(json.contains("\"name\":\"방탄소년단\""));
    }

    @Test
    void testSearchCategoryByName() {
        CategoryTree tree = new CategoryTree();
        tree.addCategory(1, "남자");
        tree.addCategory(2, "방탄소년단");
        tree.addRelationship(1, 2);
        tree.buildTree();

        Category category = tree.getCategoryByName("남자");
        assertNotNull(category);
        assertEquals("남자", category.getName());
    }

    @Test
    void testAnonymousBoardInMultipleCategories() {
        CategoryTree tree = new CategoryTree();

        // 1단계 카테고리
        tree.addCategory(1, "남자");
        tree.addCategory(2, "여자");

        // 2단계 카테고리
        tree.addCategory(3, "방탄소년단");
        tree.addCategory(4, "블랙핑크");

        // 3단계
        tree.addCategory(5, "공지사항");
        tree.addCategory(6, "익명게시판");

        // 관계 설정
        tree.addRelationship(1, 3);  // 남자 -> 방탄소년단
        tree.addRelationship(1, 5);  // 남자 -> 공지사항
        tree.addRelationship(2, 4);  // 여자 -> 블랙핑크
        tree.addRelationship(2, 5);  // 여자 -> 공지사항
        tree.addRelationship(3, 6);  // 방탄소년단 -> 익명게시판
        tree.addRelationship(4, 6);  // 블랙핑크 -> 익명게시판

        tree.buildTree();

        Category maleCategory = tree.getCategoryById(1);    // 남자
        Category femaleCategory = tree.getCategoryById(2);  // 여자
        Category anonymousBoard = tree.getCategoryById(6);  // 익명게시판

        // 남자의 모든 후손(방탄소년단, 하위 카테고리들) 중 익명게시판이 있는지?
        assertTrue(maleCategory.containsDescendant(anonymousBoard));
        // 여자의 모든 후손(블랙핑크, 하위 카테고리들) 중 익명게시판이 있는지?
        assertTrue(femaleCategory.containsDescendant(anonymousBoard));
    }
}