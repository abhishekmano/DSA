package Solutions.HashMap;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder build = new StringBuilder();
        for (int idx = 0; idx < strs.size(); ++idx) {
            String str = strs.get(idx);
            for (char ch : str.toCharArray()) {
                if (ch == '/') {
                    build.append('/');
                }
                build.append(ch);

            }
            if (idx != strs.size() - 1)
                build.append("/:");
        }
        return build.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        StringBuilder build = new StringBuilder();
        for (int idx = 0; idx < n; ++idx) {
            char ch = s.charAt(idx);
            if (ch == '/') {
                if (s.charAt(idx + 1) == '/') {
                    build.append(ch);
                    idx++;
                } else {
                    result.add(build.toString());
                    idx++;
                    build = new StringBuilder();
                }
            } else {
                build.append(ch);
            }
        }
        result.add(build.toString());
        return result;

    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
