package vip.floatationdevice.dancegrade.data.http;

import lombok.Data;
import vip.floatationdevice.dancegrade.data.DanceData;

import java.util.List;

@Data
public class SearchResult
{
    private final List<DanceData> result;
    private final int total;
}
