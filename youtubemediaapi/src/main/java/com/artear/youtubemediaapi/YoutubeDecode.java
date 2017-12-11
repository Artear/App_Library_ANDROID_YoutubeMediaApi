package com.artear.youtubemediaapi;

import android.net.Uri;

import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.QualityType;
import com.artear.youtubemediaapi.model.YoutubeMedia;
import com.artear.youtubemediaapi.model.YoutubeMediaType;
import com.artear.youtubemediaapi.model.YoutubeMetaData;
import com.artear.youtubemediaapi.model.YoutubeSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class YoutubeDecode {

    private String id;
    private String content;

    public YoutubeDecode(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public YoutubeDecode(String id) {
        this.id = id;
    }

    public YoutubeMetaData parse() throws YoutubeMediaApiException {

        if (content == null) {
            throw new YoutubeMediaApiException(YoutubeErrorType.WITHOUTDATA);
        }

        Uri paramsYoutube = Uri.parse("http://yt?" + content);

        if (paramsYoutube.getPath() == null) {
            throw new YoutubeMediaApiException(YoutubeErrorType.WITHQUERYITEMS);
        }

        Set<String> paramsYoutubeItems = paramsYoutube.getQueryParameterNames();

        if (paramsYoutubeItems.contains("errorcode")) {
            int errorCode = 0;
            String code = paramsYoutube.getQueryParameter("errorcode");
            if (code != null) {
                errorCode = Integer.parseInt(code);
            }

            if (paramsYoutubeItems.contains("reason")) {

                String errorReason = paramsYoutube.getQueryParameter("reason");
                if (errorReason != null) {
                    YoutubeMediaApiException exception = new YoutubeMediaApiException(YoutubeErrorType.SERVER_ERROR);
                    exception.setCode(errorCode);
                    exception.setReason(errorReason);
                    throw exception;
                } else {
                    throw new YoutubeMediaApiException(YoutubeErrorType.UNKNOWN);
                }

            }
        }

        YoutubeMetaData youtubeMetaData = new YoutubeMetaData(id);

        List<YoutubeSource> sources = new ArrayList<>();

        for (String item :
                paramsYoutubeItems) {

            String value = paramsYoutube.getQueryParameter(item);
            switch (item) {
                case "title": {
                    youtubeMetaData.setTitle(value);
                    break;
                }
                case "keywords": {
                    youtubeMetaData.setKeywords(value
                            .replace("+", " ")
                            .split(","));
                    break;
                }

                case "url_encoded_fmt_stream_map": {

                    if (value != null && !value.equals("")) {

                        youtubeMetaData.setMediaType(YoutubeMediaType.VIDEO);
                        //Log.d("YoutubeDecode", "url_encoded_fmt_stream_map: ---------- " + value);

                        Uri paramsYoutubeMap = Uri.parse("http://map?" + value);

                        List<String> qualityList = paramsYoutubeMap.getQueryParameters("quality");
                        List<String> urlList = paramsYoutubeMap.getQueryParameters("url");

                        for (int i = 0; i < qualityList.size(); i++) {
                            QualityType type = QualityType.adaptative;
                            if (qualityList.get(i).contains("hd720")) {
                                type = QualityType.hd720;
                            }
                            if (qualityList.get(i).contains("medium")) {
                                type = QualityType.medium;
                            }
                            if (qualityList.get(i).contains("small")) {
                                type = QualityType.small;
                            }
                            sources.add(new YoutubeSource(urlList.get(i), type));
                        }
                    }
                    break;
                }

                case "hlsvp": {

                    youtubeMetaData.setMediaType(YoutubeMediaType.LIVE);
                    //its alive
                    YoutubeSource source = new YoutubeSource(value, QualityType.adaptative);
                    sources.add(source);
                    break;
                }

            }
        }
        youtubeMetaData.setYoutubeMedia(
                new YoutubeMedia(
                        sources.toArray(
                                new YoutubeSource[sources.size()]
                        )
                )
        );
        return youtubeMetaData;
            /*guard let value = item.value else {
                continue
            }

            switch item.name {
                case "title":
                    YTModel.title = value.replacingOccurrences(of: "+", with: " ")
                    break
                case "keywords":
                    YTModel.keywords = value.components(separatedBy: ",").map({ (d) -> String in
                    return d.replacingOccurrences(of: "+", with: " ")
                })
                break
                case "player_response":
                    guard
                    let response = value.toJson(),
                    let videoDetails = response["videoDetails"] as? [Any]
                    else {
                    continue
                }
                print(response)
                break
                default:
                    print("> NAME --------------------------")
                    print("> \(item.name)")
                    print("> VALUE -------------------------")
                    print("> \(value)")
                    print("> -------------------------------")
                    print(" ")
                    break
            }
        }

        return YTModel*/
    }
}
