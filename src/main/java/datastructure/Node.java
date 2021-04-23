package datastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * 树结点
 * @author otfot
 * @date 2021/04/23
 */
public class Node <T extends Comparable<T>> {


        @Getter
        @Setter
        private T data;

        @Getter
        @Setter
        private int count = 1;

        @Getter
        @Setter
        private datastructure.Node<T> left;

        @Getter
        @Setter
        private datastructure.Node<T> right;

        public Node(T data) {
            this.data = data;

        }

}
