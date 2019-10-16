package stack;

import java.util.Stack;

/**
 * 71. Simplify Path
 * Medium
 *
 * Share
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the
 * directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 *
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash /
 * between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the
 * canonical path must be the shortest string representing the absolute path.
 *
 * Example 1:
 *
 * Input: "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * Example 4:
 *
 * Input: "/a/./b/../../c/"
 * Output: "/c"
 * Example 5:
 *
 * Input: "/a/../../b/../c//.//"
 * Output: "/c"
 * Example 6:
 *
 * Input: "/a//b////c/d//././/.."
 * Output: "/a/b/c"
 */
public class SimplifyPath {

    /**
     * If you add paths to stack then it's easy to simplify special symbols - pop from stack eq to ..
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        //parse string to initial tokens
        String[] vals = path.split("/");
        //start iterating on path segments using stack to store results. Most tokens we can simply ignore
        Stack<String> s = new Stack();
        for (String p : vals) {
            //these cases refer to current dir and multiple "/"
            if (p.length() == 0 || p.equals("."))
                continue;
            //in case we have to go back - just pop from stack
            else if (p.equals("..")) {
                if (!s.isEmpty())
                    s.pop();
            }
            //all other cases - push to stack
            else
                s.push(p);
        }
        //no build the final path string
        String res = "";
        while (!s.isEmpty()) {
            res = "/" + s.pop() + res;
        }
        //in case we haven't met any path segments - return just root folder
        return res.length() == 0 ? "/" : res;
    }
}
