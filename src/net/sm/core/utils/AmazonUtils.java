package net.sm.core.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmazonUtils {

    public static final String PATTERN_PRODUCT_CODE_START = "(^https:\\/\\/www\\.amazon\\.[aA-zZ]+\\/.*dp\\/|^https:\\/\\/www\\.amazon\\.[aA-zZ]+\\/.*product\\/)";

    public static String getProductCodeByURL(String url) {

        Matcher matcher = Pattern.compile(PATTERN_PRODUCT_CODE_START).matcher(url);
        if (matcher.find()) {

            String match = matcher.group();

            String productCode = url.substring(match.length());
            int indexOf = productCode.indexOf('/');
            if (!(indexOf > -1))
                indexOf = productCode.indexOf('?');

            return (indexOf > -1) ? productCode.substring(0, indexOf) : productCode;
        }

        return null;
    }

    public static String getProductNameByPageSource(Document pageSource) {

        Element productNameElement = pageSource.getElementById("productTitle");
        if (productNameElement != null) {

            String productName = productNameElement.text();
            if (!productName.trim().isEmpty())
                return productName;
        }

        return null;
    }

    private static String getProductImageURLByPageSource(Document pageSource) {

        Element imgTagWrapperId = pageSource.getElementById("imgTagWrapperId");
        if (imgTagWrapperId != null) {
            Elements imgTag = imgTagWrapperId.getElementsByTag("img");
            if (imgTag != null) {
                String imageLink = imgTag.attr("src");
                if (!imageLink.trim().isEmpty())
                    return imageLink;
            }
        }

        return null;
    }

    private static BigDecimal getProductPriceByPageSource(Document pageSource) {

        Element priceElement = pageSource.getElementById("priceblock_ourprice");
        if (priceElement == null) {
            priceElement = pageSource.getElementById("buyNewSection");
            if (priceElement != null) {
                Elements elementsByClass = priceElement.getElementsByClass("offer-price");
                if (elementsByClass != null)
                    priceElement = elementsByClass.first();
                else
                    priceElement = null;
            }
        }

        if (priceElement != null) {

            String priceFullText = priceElement.text();
            if (!priceFullText.trim().isEmpty()) {

                Matcher matcher = Pattern.compile("[0-9]+(,[0-9]+)*").matcher(priceFullText);
                if (matcher.find()) {

                    String priceText = matcher.group();

                    DecimalFormat df = new DecimalFormat();
                    df.setParseBigDecimal(true);

                    try {
                        return (BigDecimal) df.parse(priceText);
                    } catch (ParseException e) {
                    }
                }
            }
        }

        return null;
    }
}
