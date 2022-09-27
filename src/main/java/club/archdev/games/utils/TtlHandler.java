package club.archdev.games.utils;

/**
 * Made By Trixkz (LoganM) - trixkz.me
 * Project: Games
 */
public interface TtlHandler<E> {

    void onExpire(E element);

    long getTimestamp(E element);
}
