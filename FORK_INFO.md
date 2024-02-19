# Fork Information

This Bloomreach fork builds on [paultuckey/urlrewritefilter](https://github.com/paultuckey/urlrewritefilter).

## Branching and Versioning

The `main` branch is not be committed to, so it can always easily be merged with Tuckey's main branch.

The branches `bloomreach/5.x` and  `bloomreach/4.x` contain Bloomreach-specific commits and can be reintegrated from 
Tuckey's `main` and `4.x`, respectively.

A Bloomreach-specific urlrewritefilter version adds a -h1 (-h2, -h3 etc.) postfix to the version it extends, where 'h' 
stands for 'Hippo CMS', the former name of the product. For example, version `5.1.3-h1` extends urlrewritefilter `5.1.3`. 

## Releases

### Releases 5.1.3-h1

_Release Date: expected Q1 2024_

Built on Java 17 for brXM 16.

Reimplement the Bloomreach-specific changes on bloomreach/4.x in the bloomreach/5.x branch.

- **Refactored** RequestProxy:
  - [ENT-1194](https://issues.onehippo.com/browse/ENT-1194) use org.apache.httpcomponents:httpclient:4.5.x
  - [HIPPLUG-1606](https://issues.onehippo.com/browse/HIPPLUG-1606) introduce followRedirects attribute to enable redirects
  - [HIPPLUG-1606](https://issues.onehippo.com/browse/HIPPLUG-1606) introduce useSystemProperties attribute to use system properties when creating and configuring the http client
  - Set drop-cookies to "false" per default
  - [HIPPLUG-1646](https://issues.onehippo.com/browse/HIPPLUG-1646) do not recreate the URL object with the default 
   port, as it can break SSL offloading.

- [HIPPLUG-1587](https://issues.onehippo.com/browse/HIPPLUG-1587) in XML rule, accept CDATA values in elements, so 
  escaping is not needed in advanced values

- [HIPPLUG-1570](https://issues.onehippo.com/browse/HIPPLUG-1570) add a facility to UrlRewriteWrappedResponse to inject disallowed duplicate header names.

- In status page, show both original 'from' and the encoded 'from'

- [HIPPLUG-1419](https://issues.onehippo.com/browse/HIPPLUG-1419) make redirects work on URLs with non-ascii characters. 
  e.g. Cyrillic

- Validate for invalid CR/LF characters in cookie keys or values


### Releases 4.0.4-h9

_Latest Release Date: 13 February 2020_

Built for brXM 15 and lower.

See [bloomreach/4.x/FORK_INFO.md](https://github.com/bloomreach/urlrewritefilter/blob/bloomreach/4.x/FORK_INFO.md) for full changelog.
