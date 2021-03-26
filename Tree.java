public class Tree {
    private class Node{
        private Integer val;
        Node parent;
        Node left;
        Node right;

        public Node(Integer val) {
            this.val = val;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", parent=" + parent +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root = new Node(null);
    private Integer elements = 0;
    private Integer sum;

    public boolean isEmpty(){
        return elements == 0;
    }

    public void add(Integer val){
        if(root.getVal() == null){
            root = new Node(val);
        }
        else{
            Node node = new Node(val);
            Node currentNode = root;

            while(true){
                if(val > currentNode.getVal()){
                    if(currentNode.right == null){
                        currentNode.right = node;
                        node.parent = currentNode;
                        break;
                    }
                    else{
                        currentNode = currentNode.right;
                    }
                }
                else if(val < currentNode.getVal()){
                    if(currentNode.left == null){
                        currentNode.left = node;
                        node.parent = currentNode;
                        break;
                    }
                    else {
                        currentNode = currentNode.left;
                    }
                }
                else{
                    return;
                }
            }
        }
        elements++;
    }

    public void remove(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.val) {
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            int smallestValue = findMinVal(current.right);
            current.val = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.val) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findMinVal(Node root) {
        return root.left == null ? root.val : findMinVal(root.left);
    }

    private Node next(Integer val){
        Node currentNode = root;
        while(true){
            if(currentNode.getVal() == val){
                if (currentNode.right != null)
                    return minimum(currentNode.right);
                while (currentNode.parent != null && currentNode ==currentNode.parent.right) {
                    currentNode = currentNode.parent;
                    currentNode.parent = currentNode.parent.parent;
                }
                return currentNode.parent;
            }
            else if(val > currentNode.getVal()){
                currentNode = currentNode.right;
            }
            else if(val < currentNode.getVal()){
                currentNode = currentNode.left;
            }
        }
    }

    private Node minimum(Node x) {
        if (x.left == null)
            return x;
        return minimum(x.left);
    }


    public Node elementFind(Integer val) {
        Node currentNode = root;
        while (true) {
            if (currentNode.getVal() == val) {
                return currentNode;
            }
            if(val > currentNode.getVal()){
                currentNode = currentNode.right;
            }
            else if(val < currentNode.getVal()){
                currentNode = currentNode.left;
            }
        }
    }

    public Integer search(int v) {
        Node current = root;
        while (current != null) {
            if (current.val == v)
                return current.val;
            current = v < current.val ? current.left : current.right;
        }
        return null;
    }

    public long sum(){
        return getSum(root);
    }


    private long getSum(Node currentNode)
    {
        if (currentNode == null) return 0;

        return getSum(currentNode.left) + currentNode.getVal() + getSum(currentNode.right);
    }
}


