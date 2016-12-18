
public class PalindromicTree {

	Node fictive = new Node(-1);
	Node empty = new Node(0);

	public PalindromicTree() {
		empty.suffixLink = fictive;
		fictive.suffixLink = fictive;
	}

	public String longestPalindromeSubstring(String S) {
		int longest_palindrome_end = -1;
		int length = 0;
		Node T = empty;
		/*
		 * T is the largest proper palindrome suffix for the current prefix
		 */
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);

			Node current = T;
			while (current != fictive) {
				int index = i - current.length - 1;
				if (index >= 0 && S.charAt(index) == c) {
					break;
				}

				current = current.suffixLink;
			}
			Node newNode = null;
			if (current == fictive) {
				newNode = new Node(1);
				try {
					fictive.edges[S.charAt(i)] = newNode;
				} catch (Exception e) {
					System.out.println(i + " " + S.charAt(i));
					throw (e);
				}
			} else {
				newNode = new Node(current.length + 2);
				current.edges[S.charAt(i)] = newNode;
			}
			// now find the largest proper palindrome suffix for the new node
			// palindrome

			if (current == fictive) {
				newNode.suffixLink = empty;
			} else {
				current = current.suffixLink;
				while (current != fictive) {
					int index = i - current.length - 1;
					if (index >= 0 && S.charAt(index) == c) {
						break;
					}
					current = current.suffixLink;
				}
				newNode.suffixLink = current.edges[c];
			}
			T = newNode;
			if (T.length > length) {
				length = T.length;
				longest_palindrome_end = i;
			}
		}

		String longest_palindrome = "";
		if (longest_palindrome_end != -1) {
			longest_palindrome = S.substring(longest_palindrome_end - length + 1, longest_palindrome_end + 1);
		}

		return longest_palindrome;
	}
}
