import java.util.ArrayList;
import java.util.List;

public class Category {
    private static Category root = new Category("Каталог", null);
    private String categoryName;
    private List<Category> parentNodes = new ArrayList<>();
    private List<Category> childNodes = new ArrayList<>();

    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        if (parent != null) {
            parentNodes.add(parent);
            parent.getChildNodes().add(this);
        }
    }

    public static Category getRoot() {
        return root;
    }

    public List<Category> getChildNodes() {
        return childNodes;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public String getTreeToString() {
        return this.getTreeRecursion(0, new StringBuilder()).toString();
    }

    private StringBuilder getTreeRecursion(int i, StringBuilder sb) {
        for (int j = 0; j < i; j++) {
            sb.append('|');
        }
        i++;
        sb.append(this.categoryName).append('\n');
        if (this.getChildNodes().size() > 0) {
            for (Category c : this.getChildNodes()) {
                c.getTreeRecursion(i, sb);
            }
        }
        return sb;
    }

    Category searchCategoryByName(String categoryName) {
        return this.searchCategoryByNameRecursion(categoryName, new ArrayList<Category>());
    }

    Category searchCategoryByNameRecursion(String categoryName, List<Category> categories) {
        if (this.categoryName.equals(categoryName)) {
            categories.add(this);
            return categories.get(0);
        }
        if (this.getChildNodes().size() > 0) {
            for (Category c : this.getChildNodes()) {
                c.searchCategoryByNameRecursion(categoryName, categories);
            }
        }
        return categories.size() > 0 ? categories.get(0) : null;
    }
}