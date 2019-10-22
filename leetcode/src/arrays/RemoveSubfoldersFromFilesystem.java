package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1233. Remove Sub-Folders from the Filesystem
 * Medium
 *
 * Given a list of folders, remove all sub-folders in those folders and return in any order the
 * folders after removing.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or
 * more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths
 * while an empty string and / are not.
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in
 * our filesystem.
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 *
 * Constraints:
 *
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'
 * folder[i] always starts with character '/'
 * Each folder name is unique.
 *
 */
public class RemoveSubfoldersFromFilesystem {

  /**
   * If we sort we get the sequence where each next string is either a subfolder or next parent.
   * Just save the prev parent and check next string if it's child or next parent.
   * @param folder
   * @return
   */
  public List<String> removeSubfolders(String[] folder) {
    List<String> res = new ArrayList();
    //sort lexigraphicaly, this makes child be after the parent
    Arrays.sort(folder);
    res.add(folder[0]);
    //save first element as prev - it's always parent
    String prev = folder[0];
    //start scaning foldrs start from 1 index
    for (int i = 1; i < folder.length; i++) {
      String next = folder[i];
      //if folder name start from the prev string and next char after that is "/" - this
      //is a subfolder and we can skip it
      if (next.startsWith(prev) && next.charAt(prev.length()) == '/') {
        continue;
      }
      //otherwise this is a parent - need to add to result and make it next prev
      else {
        res.add(next);
        prev = next;
      }
    }
    return res;
  }
}
