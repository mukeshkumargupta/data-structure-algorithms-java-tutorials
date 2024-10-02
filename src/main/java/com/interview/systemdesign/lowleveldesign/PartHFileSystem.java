package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Here’s a complete solution for designing a simple file system in Java
 * that supports file creation, deletion, reading, and writing. This
 * implementation includes classes for File, Directory, and FileSystem,
 * along with the necessary functionality to manage files and directories.
 *
 * Explanation of the Code:
 * - **File Class**:
 *   Represents a file with attributes like name, content, and owner.
 *   It provides methods for reading and writing the file content.
 *
 * - **Directory Class**:
 *   Represents a directory that can contain files and subdirectories.
 *   It provides methods to add/remove files and subdirectories and list them.
 *
 * - **FileSystem Class**:
 *   Manages the root directory and provides methods for creating and
 *   deleting files and directories. It also allows reading and writing
 *   file content.
 *
 * - **Main Class**:
 *   Demonstrates how to use the file system by creating directories and
 *   files, writing to files, and reading file content.
 *
 * Considerations:
 * - **Hierarchical Structure**:
 *   The file system supports nested directories, allowing for a
 *   hierarchical organization of files.
 *
 * - **File Permissions and Ownership**:
 *   Basic ownership is managed, but permission management can be further
 *   enhanced based on specific requirements.
 *
 * - **Error Handling**:
 *   The implementation can be expanded with error handling for various
 *   operations, such as attempting to access non-existent files or
 *   directories.
 *
 * This design provides a solid foundation for a file system and can be
 * further extended with additional features, such as file permissions,
 * version control, or file search functionality.
 */

// Class representing a file in the file system
class File {
    private String name;
    private String content;
    private String owner;

    public File(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.content = ""; // Initially empty
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String read() {
        return content;
    }

    public void write(String content) {
        this.content = content;
    }
}

// Class representing a directory in the file system
class Directory {
    private String name;
    private Map<String, File> files;
    private Map<String, Directory> subdirectories;

    public Directory(String name) {
        this.name = name;
        this.files = new HashMap<>();
        this.subdirectories = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addFile(File file) {
        files.put(file.getName(), file);
    }

    public void removeFile(String fileName) {
        files.remove(fileName);
    }

    public File getFile(String fileName) {
        return files.get(fileName);
    }

    public void addSubdirectory(Directory directory) {
        subdirectories.put(directory.getName(), directory);
    }

    public void removeSubdirectory(String dirName) {
        subdirectories.remove(dirName);
    }

    public Directory getSubdirectory(String dirName) {
        return subdirectories.get(dirName);
    }

    public List<File> listFiles() {
        return new ArrayList<>(files.values());
    }

    public List<Directory> listSubdirectories() {
        return new ArrayList<>(subdirectories.values());
    }
}

// Class representing the file system
class FileSystem {
    private Directory root;

    public FileSystem() {
        root = new Directory("root");
    }

    public Directory getRoot() {
        return root;
    }

    public void createFile(String path, String fileName, String owner) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            File file = new File(fileName, owner);
            dir.addFile(file);
        }
    }

    public void deleteFile(String path, String fileName) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            dir.removeFile(fileName);
        }
    }

    public String readFile(String path, String fileName) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            File file = dir.getFile(fileName);
            if (file != null) {
                return file.read();
            }
        }
        return null;
    }

    public void writeFile(String path, String fileName, String content) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            File file = dir.getFile(fileName);
            if (file != null) {
                file.write(content);
            }
        }
    }

    public void createDirectory(String path, String dirName) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            Directory newDir = new Directory(dirName);
            dir.addSubdirectory(newDir);
        }
    }

    public void deleteDirectory(String path, String dirName) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            dir.removeSubdirectory(dirName);
        }
    }

    private Directory navigateToDirectory(String path) {
        String[] directories = path.split("/");
        Directory currentDir = root;

        for (String dirName : directories) {
            if (!dirName.isEmpty()) {
                currentDir = currentDir.getSubdirectory(dirName);
                if (currentDir == null) {
                    return null; // Directory not found
                }
            }
        }
        return currentDir;
    }
}

// Main class to demonstrate the file system
public class PartHFileSystem {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        // Create directories
        fs.createDirectory("/root", "documents");
        fs.createDirectory("/root/documents", "photos");
        fs.createDirectory("/root/documents", "reports");

        // Create files
        fs.createFile("/root/documents", "file1.txt", "user1");
        fs.createFile("/root/documents/photos", "photo1.jpg", "user2");

        // Write to a file
        fs.writeFile("/root/documents", "file1.txt", "Hello World!");

        // Read a file
        String content = fs.readFile("/root/documents", "file1.txt");
        System.out.println("Content of file1.txt: " + content);

        // List files and directories
        List<File> files = fs.getRoot().getSubdirectory("documents").listFiles();
        System.out.println("Files in documents directory:");
        for (File file : files) {
            System.out.println("- " + file.getName());
        }
    }
}
