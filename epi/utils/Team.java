package epi.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Team {
    List<Player> players;

    public Team(List<Integer> height) {
        players = height.stream()
                        .map(Player::new)
                        .collect(Collectors.toList());
    }

    private static final class Player implements Comparable<Player> {
        public Integer height;

        private Player(Integer h) { height = h; }

        @Override
        public int compareTo(Player that) {
            return Integer.compare(height, that.height);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Player)) { return false; }
            return compareTo((Player) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(height);
        }
    }
}
