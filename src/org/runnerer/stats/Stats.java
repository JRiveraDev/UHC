package org.runnerer.stats;

import org.runnerer.database.MySQL;
import org.runnerer.stats.type.StatType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats
{
    public static String retrieveUHCStats(String uuid, StatType statType)
    {
        try
        {
            if (statType == StatType.UHC_DEATHS)
            {
                ResultSet resultSet = MySQL.querySQL("SELECT * FROM uhcStats WHERE uuid = '" + uuid + "';");

                while (resultSet.next())
                {
                    String kills = resultSet.getString("deaths");

                    return kills;
                }
            } else if (statType == StatType.UHC_GAMESPLAYED)
            {
                ResultSet resultSet = MySQL.querySQL("SELECT * FROM uhcStats WHERE uuid = '" + uuid + "';");

                while (resultSet.next())
                {
                    String wins = resultSet.getString("games");

                    return wins;
                }
            } else if (statType == StatType.UHC_KILLS)
            {
                ResultSet resultSet = MySQL.querySQL("SELECT * FROM uhcStats WHERE uuid = '" + uuid + "';");

                while (resultSet.next())
                {
                    String wins = resultSet.getString("kills");

                    return wins;
                }
            } else if (statType == StatType.UHC_WINS)
            {
                ResultSet resultSet = MySQL.querySQL("SELECT * FROM uhcStats WHERE uuid = '" + uuid + "';");

                while (resultSet.next())
                {
                    String wins = resultSet.getString("wins");

                    return wins;
                }
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void addKills(String uuid, int amount)
    {
        try
        {
            MySQL.updateSQL("UPDATE duelStats SET kills='" + retrieveUHCStats(uuid, StatType.UHC_KILLS) + amount + "' WHERE uuid='" + uuid + "';");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addWins(String uuid, int amount)
    {
        try
        {
            MySQL.updateSQL("UPDATE duelStats SET games='" + retrieveUHCStats(uuid, StatType.UHC_WINS) + amount + "' WHERE uuid='" + uuid + "';");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addGamesPlayed(String uuid, int amount)
    {
        try
        {
            MySQL.updateSQL("UPDATE ffaStats SET kills='" + retrieveUHCStats(uuid, StatType.UHC_GAMESPLAYED) + amount + "' WHERE uuid='" + uuid + "';");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addDeaths(String uuid, int amount)
    {
        try
        {
            MySQL.updateSQL("UPDATE ffaStats SET deaths='" + retrieveUHCStats(uuid, StatType.UHC_DEATHS) + amount + "' WHERE uuid='" + uuid + "';");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
