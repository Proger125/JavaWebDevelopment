package edu.epam.parsing.parser;

import edu.epam.parsing.entity.Gem;

import java.util.HashSet;
import java.util.Set;

public abstract class GemBuilder {
    protected Set<Gem> gems = new HashSet<>();

    public Set<Gem> getGems() {
        return gems;
    }
}
