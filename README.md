# Jatube

A youtube downloader make with java and python. It's an application that, as said before, allow you to download files from youtube.

## Usage

After copy the repository in you local machine:

1. Move to the dir where is the project:

    ```bash
    cd /path/to/jatube/src
    ```

2. Compile the file:

    ```bash
    javac App.java
    ```

3. Run:

    ```bash
    java App
    ```

* Simplified:

    ```bash
    cd /path/to/jatube/src && javac App.java && java App
    ```

Or just execute one of the binaries in the bin dir:

```bash
cd /path/to/jatube/bin && java App
```

## Requirements

To use it you need the following languages installed:

* Java (JVM 17+)
* Python (3.10+)

For Python, you need to download the following packages:

* Pytube

**Important:** Until date (20-06-2022) Pytube has a bug with the Cypher.py script, if you have problems, could try this solution:

[![Pytube Solution](/imgs/pytube_solution.png)](https://github.com/pytube/pytube/issues/1281) _Click on image to go to the discussion_

## Authors

* [@Cromega08](https://www.github.com/cromega08)

## License

* [GNU AGPL v3.0](https://choosealicense.com/licenses/agpl-3.0/)
