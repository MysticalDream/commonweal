package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 排行测试
 */
public class SortTest {
    /**
     * 树节点
     */
    public static class TNode {
        Integer fromScore;
        Integer toScore;
        Integer count;
        TNode lChild;
        TNode rChild;

        public TNode() {

        }

        public TNode(Integer fromScore, Integer toScore, Integer count) {
            this.fromScore = fromScore;
            this.toScore = toScore;
            this.count = count;
        }

        public TNode(Integer fromScore, Integer toScore) {
            this.fromScore = fromScore;
            this.toScore = toScore;
            this.count = 0;
        }
    }

 

    /**
     * 创建树节点
     *
     * @param fromScore
     * @param toScore
     * @return
     */
    public TNode createTreeNode(Integer fromScore, Integer toScore) {
        if (fromScore > toScore) {
            return null;
        }
        TNode root = new TNode(fromScore, toScore);
        if (fromScore == toScore) {
            return root;
        }
        int mid = fromScore + ((toScore - fromScore) >>> 1);
        root.lChild = createTreeNode(fromScore, mid);
        root.rChild = createTreeNode(mid + 1, toScore);
        return root;
    }

    public void insertNewScore(TNode root, Integer score) {
        if (root == null) {
            return;
        }
        if (score >= root.fromScore && score <= root.toScore) {
            root.count++;
        }
        int mid = root.fromScore + ((root.toScore - root.fromScore) >>> 1);
        if (score <= mid) {
            insertNewScore(root.lChild, score);
        } else {
            insertNewScore(root.rChild, score);
        }
    }

    public void deleteOldScore(TNode root, Integer score) {
        if (root == null) {
            return;
        }
        if (score >= root.fromScore && score <= root.toScore) {
            root.count--;
        }
        int mid = root.fromScore + ((root.toScore - root.fromScore) >>> 1);
        if (score <= mid) {
            deleteOldScore(root.lChild, score);
        } else {
            deleteOldScore(root.rChild, score);
        }
    }

    public void changeScore(TNode root, Integer oldScore, Integer newScore) {
        deleteOldScore(root, oldScore);
        insertNewScore(root, newScore);
    }

    public int getRank(TNode root, Integer score) {
        if (root == null) {
            return -1;
        }
        int mid = root.fromScore + ((root.toScore - root.fromScore) >>> 1);
        if (score > mid) {
            return getRank(root.rChild, score);
        } else if (root.rChild != null) {
            return root.rChild.count + getRank(root.lChild, score);
        }
        return 1;
    }

    public void print(TNode root) {
        if (root != null) {
            print(root.lChild);
            System.out.println("from:" + root.fromScore + ",to:" + root.toScore + ",count:" + root.count);
            print(root.rChild);
        }
    }

    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        TNode root = sortTest.createTreeNode(1, 10);
        List<Integer> list = new ArrayList<>(List.of(1, 3, 5, 6, 2, 4, 3, 1, 7, 7));
        list.sort(Comparator.comparingInt(o -> o));
        System.out.println(list);
        list.forEach(e -> {
            sortTest.insertNewScore(root, e);
        });
        sortTest.print(root);
        System.out.println(sortTest.getRank(root, 7));
    }

}
