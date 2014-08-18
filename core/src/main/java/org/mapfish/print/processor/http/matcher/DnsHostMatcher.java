/*
 * Copyright (C) 2014  Camptocamp
 *
 * This file is part of MapFish Print
 *
 * MapFish Print is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MapFish Print is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MapFish Print.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mapfish.print.processor.http.matcher;

import com.google.common.base.Optional;
import org.mapfish.print.config.ConfigurationException;

import java.net.URI;
import java.util.List;

/**
 * Allows to check that a given URL matches a DNS address (textual format).
 * <p>Example 1: Accept any www.camptocamp.com url</p>
 * <pre><code>
 *     - !dnsMatch
 *       host : www.camptocamp.com
 * </code></pre>
 * <p>Example 2: Accept any www.camptocamp.com url (port == -1 accepts any port)</p>
 * <pre><code>
 *     - !dnsMatch
 *       host : www.camptocamp.com
 *       port : -1
 * </code></pre>
 * <p>Example 3: Accept any www.camptocamp.com url on port 80 only</p>
 * <pre><code>
 *     - !dnsMatch
 *       host : www.camptocamp.com
 *       port : 80
 * </code></pre>
 * <p>
 *     Example 4: Accept www.camptocamp.com urls with paths that start with /print/.
 *     <p>
 *         If the regular expression give does not start with / then it will be added because all paths start with /
 *     </p>
 * </p>
 * <pre><code>
 *     - !dnsMatch
 *       host : www.camptocamp.com
 *       pathRegex : /print/.+
 * </code></pre>
 */
public class DnsHostMatcher extends HostMatcher {
    private String host = null;

    /**
     * Check the given URI to see if it matches.
     *
     * @param uri the uri to validate.
     *
     * @return True if it matches.
     */
    @Override
    public final Optional<Boolean> tryOverrideValidation(final URI uri) {
        if (!uri.getHost().equals(this.host)) {
            return Optional.of(false);
        }
        return Optional.absent();
    }
    @Override
    public final void validate(final List<Throwable> validationErrors) {
        if (this.host == null) {
            validationErrors.add(new ConfigurationException("No host defined: " + getClass().getName()));
        }
    }

    public final void setHost(final String host) {
        this.host = host;
    }

    // CHECKSTYLE:OFF
    // Don't run checkstyle on generated methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DnsHostMatcher");
        sb.append("{host='").append(host).append('\'');
        if (port >= 0) {
            sb.append(", port=").append(port);
        }
        if (pathRegex != null) {
            sb.append(", pathRegexp=").append(pathRegex);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DnsHostMatcher other = (DnsHostMatcher) obj;
        if (host == null) {
            if (other.host != null) {
                return false;
            }
        } else if (!host.equals(other.host)) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
