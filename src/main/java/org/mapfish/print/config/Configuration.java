package org.mapfish.print.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

import org.json.JSONWriter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * The Main Configuration Bean.
 *
 * Created by Jesse on 2/20/14.
 */
public class Configuration {
    private boolean reloadConfig;
    private String proxyBaseUrl;
    private TreeSet<String> headers;

    public static Configuration loadFile(String configFile) throws IOException {
        return loadFile(new File(configFile));
    }

    public static Configuration loadFile(File configFile) throws IOException {
        Yaml yaml = new Yaml(new Constructor(Configuration.class));

        FileInputStream in = null;
        try {
            in  = new FileInputStream(configFile);
            return (Configuration) yaml.load(new InputStreamReader(in, "UTF-8"));
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    public void printClientConfig(JSONWriter json) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    public boolean isReloadConfig() {
        return reloadConfig;
    }

    public void setReloadConfig(boolean reloadConfig) {
        this.reloadConfig = reloadConfig;
    }

    public String getProxyBaseUrl() {
        return proxyBaseUrl;
    }

    public void setProxyBaseUrl(String proxyBaseUrl) {
        this.proxyBaseUrl = proxyBaseUrl;
    }

    public TreeSet<String> getHeaders() {
        return headers;
    }

    public void setHeaders(TreeSet<String> headers) {
        this.headers = headers;
    }

    public String getOutputFilename(String layoutName) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

}
