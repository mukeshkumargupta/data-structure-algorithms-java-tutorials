package com.interview.suffixprefix;

import org.junit.Assert;
import org.junit.Test;

import com.interview.trie.Part_A_A_Trie;

public class PartATrieTest {

    @Test
    public void testDifferentCases() {
        Part_A_A_Trie partATrie = new Part_A_A_Trie();

        partATrie.insert("abcd");
        partATrie.insert("abgl");
        partATrie.insertRecursive("lmn");
        partATrie.insertRecursive("lmnpq");
        partATrie.insert("cdeg");
        partATrie.insert("ghijk");

        Assert.assertFalse(partATrie.search("ab"));
        Assert.assertFalse(partATrie.search("abc"));
        Assert.assertTrue(partATrie.search("abcd"));
        Assert.assertFalse(partATrie.search("abg"));
        Assert.assertTrue(partATrie.search("abgl"));
        Assert.assertFalse(partATrie.search("lm"));
        Assert.assertTrue(partATrie.search("lmn"));
        Assert.assertFalse(partATrie.search("lmnp"));
        Assert.assertTrue(partATrie.search("lmnpq"));

        partATrie.delete("abcd");
        Assert.assertTrue(partATrie.search("abgl"));
        Assert.assertFalse(partATrie.search("abcd"));

        partATrie.delete("lmn");
        Assert.assertFalse(partATrie.search("lmn"));
        Assert.assertTrue(partATrie.search("lmnpq"));

        partATrie.delete("lmnpq");
        Assert.assertFalse(partATrie.search("lmnpq"));

    }
}
