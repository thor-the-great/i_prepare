package graphs;

import java.util.*;

/**
 * 721. Accounts Merge
 * Medium
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a
 * name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some
 * email that is common to both accounts. Note that even if two accounts have the same name, they may belong to
 * different people as people could have the same name. A person can have any number of accounts initially, but all of
 * their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the
 * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */
public class MergeAccounts {

    /**
     * Create map of unique emails, map each email to id. Put those ids to UnionFind. Then start checking each unique
     * email and union then in UF. This gives all emails for one name joined.
     * Then extract the joined sets, sort and return
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UF uf = new UF();
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailId = new HashMap();

        int idx = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            String mainMail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                if (!emailId.containsKey(email)) {
                    emailId.put(email, idx);
                    uf.union(emailId.get(mainMail), idx);
                    idx++;
                } else {
                    uf.union(emailId.get(mainMail), emailId.get(email));
                }
            }
        }

        Map<Integer, List<String>> res = new HashMap();
        for (String email : emailToName.keySet()) {
            int i = uf.find(emailId.get(email));
            if (!res.containsKey(i)) {
                res.put(i, new ArrayList());
            }
            res.get(i).add(email);
        }
        List<List<String>> ans = new ArrayList();
        for (List<String> els : res.values()) {
            Collections.sort(els);
            els.add(0, emailToName.get(els.get(0)));
            ans.add(els);
        }
        return ans;
    }

    class UF {
        int[] parent;
        int[] rank;

        UF() {
            parent = new int[10001];
            for (int i = 0; i <= 10000; i++) {
                parent[i] = i;
            }
            rank = new int[10001];
        }

        void union(int a, int b) {
            int rankA = rank[a];
            int rankB = rank[b];

            if (rankA > rankB) {
                parent[find(b)] = find(a);
            } else if (rankA < rankB) {
                parent[find(a)] = find(b);
            } else {
                parent[find(a)] = find(b);
                rank[b]++;
            }
        }

        int find(int a) {
            if (parent[a] != a)
                parent[a] = find(parent[a]);
            return parent[a];
        }
    }
}
