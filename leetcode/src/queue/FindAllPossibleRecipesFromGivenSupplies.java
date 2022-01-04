package queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * 2115. Find All Possible Recipes from Given Supplies
Medium

You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

 

Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 

Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.

https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    
    /**
     * Check all ingridience from recipe if all are present - remove this recipe from the list and add
     * recipe name to the list of supplies. Keep doing this until we use all recipes or we cannot create
     * any recipe 
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        //set of all supplies
        Set<String> suppl = new HashSet<>();
        for (String s : supplies) {
            suppl.add(s);
        }
        //queue with indexes of each recipe that isn't completed yet
        Deque<Integer> q = new LinkedList();
        for (int i = 0; i < recipes.length; i++) {
            q.add(i);
        }
        List<String> res = new ArrayList<>();
        while(!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int i = q.pollFirst();
                //checking if we have all ingridience for recipe
                List<String> ing = ingredients.get(i);
                ListIterator<String> li = ing.listIterator();
                while (li.hasNext()) {
                    if (suppl.contains(li.next())) {
                        li.remove();
                    }
                }
                if (ing.size() == 0) {
                    res.add(recipes[i]);
                    //now add the resulting product to the list of supplies
                    suppl.add(recipes[i]);
                } else {
                    //when we don't have all ingridience - put i back to queue, try next time
                    q.addLast(i);
                }
            }
            if (size == q.size()) {
                break;
            }
        }
        
        return res;
    }
}
