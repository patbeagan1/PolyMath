#!/usr/bin/env python3

import os
import yaml
from pathlib import Path
from typing import Dict, List, Set, Optional

def detect_language(project_path: Path) -> Optional[str]:
    """Detect the primary language of a project based on file extensions and structure."""
    extensions = set()
    has_gradle = False
    has_cargo = False
    has_package_json = False
    has_pom_xml = False
    has_makefile = False
    has_cmake = False
    
    # Directories to ignore when scanning for language detection
    ignore_dirs = {'.idea', '.vscode', '.gradle', 'target', 'build', 'node_modules', 'venv', '__pycache__', '.git'}
    
    for file_path in project_path.rglob('*'):
        if file_path.is_file():
            # Skip files in ignored directories
            if any(ignore_dir in file_path.parts for ignore_dir in ignore_dirs):
                continue
                
            ext = file_path.suffix.lower()
            extensions.add(ext)
            
            if file_path.name == 'build.gradle' or file_path.name == 'build.gradle.kts':
                has_gradle = True
            elif file_path.name == 'Cargo.toml':
                has_cargo = True
            elif file_path.name == 'package.json':
                has_package_json = True
            elif file_path.name == 'pom.xml':
                has_pom_xml = True
            elif file_path.name == 'Makefile':
                has_makefile = True
            elif file_path.name == 'CMakeLists.txt':
                has_cmake = True
    
    # Language detection logic
    if has_cargo:
        return 'rust'
    elif has_gradle:
        return 'kotlin'
    elif has_pom_xml:
        return 'java'
    elif has_package_json:
        return 'javascript'
    elif has_makefile or has_cmake:
        return 'c'
    elif '.py' in extensions:
        return 'python'
    elif '.kt' in extensions:
        return 'kotlin'
    elif '.java' in extensions:
        return 'java'
    elif '.lua' in extensions:
        return 'lua'
    elif '.go' in extensions:
        return 'go'
    elif '.hs' in extensions:
        return 'haskell'
    elif '.clj' in extensions or '.cljc' in extensions:
        return 'clojure'
    elif '.cpp' in extensions or '.cc' in extensions or '.cxx' in extensions:
        return 'cpp'
    elif '.c' in extensions:
        return 'c'
    elif '.html' in extensions or '.js' in extensions or '.css' in extensions:
        return 'javascript'
    elif '.rs' in extensions:
        return 'rust'
    elif '.rb' in extensions:
        return 'ruby'
    elif '.php' in extensions:
        return 'php'
    elif '.swift' in extensions:
        return 'swift'
    elif '.scala' in extensions:
        return 'scala'
    elif '.r' in extensions:
        return 'r'
    elif '.m' in extensions or '.mm' in extensions:
        return 'objective-c'
    elif '.sh' in extensions or '.zsh' in extensions or '.bash' in extensions:
        return 'shell'
    elif '.pl' in extensions or '.pm' in extensions:
        return 'perl'
    elif '.ex' in extensions or '.exs' in extensions:
        return 'elixir'
    elif '.erl' in extensions:
        return 'erlang'
    elif '.ml' in extensions or '.mli' in extensions:
        return 'ocaml'
    elif '.fs' in extensions or '.fsx' in extensions:
        return 'fsharp'
    elif '.dart' in extensions:
        return 'dart'
    elif '.nim' in extensions:
        return 'nim'
    elif '.zig' in extensions:
        return 'zig'
    elif '.v' in extensions:
        return 'v'
    elif '.cr' in extensions:
        return 'crystal'
    elif '.jl' in extensions:
        return 'julia'
    elif '.gd' in extensions:
        return 'gdscript'
    elif '.cs' in extensions:
        return 'csharp'
    elif '.vb' in extensions:
        return 'vb'
    elif '.pas' in extensions or '.pp' in extensions:
        return 'pascal'
    elif '.f90' in extensions or '.f95' in extensions:
        return 'fortran'
    elif '.asm' in extensions or '.s' in extensions:
        return 'assembly'
    elif '.sql' in extensions:
        return 'sql'
    elif '.tex' in extensions:
        return 'latex'
    elif '.json' in extensions and len(extensions) == 1:
        return 'json'
    elif '.md' in extensions and len(extensions) == 1:
        return 'markdown'
    elif '.yaml' in extensions or '.yml' in extensions:
        return 'yaml'
    elif '.toml' in extensions:
        return 'toml'
    elif '.xml' in extensions:
        return 'xml'
    elif '.csv' in extensions:
        return 'csv'
    elif '.txt' in extensions and len(extensions) == 1:
        return 'text'
    
    return None

def generate_description(project_name: str, language: Optional[str]) -> str:
    """Generate a basic description for the project."""
    if language:
        return f"A {language} project"
    return "A software project"

def generate_tags(project_name: str, language: Optional[str]) -> List[str]:
    """Generate appropriate tags based on project name and language."""
    tags = []
    
    # Language-based tags
    if language:
        tags.append(language)
    
    # Project type tags based on name patterns
    name_lower = project_name.lower()
    
    if 'demo-' in name_lower:
        tags.append('demo')
    elif 'util-' in name_lower:
        tags.append('utility')
    elif 'game-' in name_lower:
        tags.append('game')
    elif 'website-' in name_lower:
        tags.append('website')
    elif 'app-' in name_lower:
        tags.append('app')
    elif 'lib-' in name_lower:
        tags.append('library')
    elif 'extension-' in name_lower:
        tags.append('extension')
    elif 'data-' in name_lower:
        tags.append('data')
    elif 'ai-' in name_lower:
        tags.append('ai')
    
    # Additional tags based on project structure
    project_path = Path(project_name)
    if (project_path / 'app').exists() or (project_path / 'src').exists():
        tags.append('application')
    if (project_path / 'test').exists() or (project_path / 'tests').exists():
        tags.append('testing')
    if (project_path / 'docs').exists() or (project_path / 'documentation').exists():
        tags.append('documentation')
    
    return tags

def create_project_meta(project_path: Path) -> Dict:
    """Create a project.meta.yaml content for a given project."""
    project_name = project_path.name
    language = detect_language(project_path)
    description = generate_description(project_name, language)
    tags = generate_tags(project_name, language)
    
    meta = {
        'name': project_name,
        'description': description,
        'tags': tags
    }
    
    if language:
        meta['language'] = language
    
    return meta

def main():
    """Generate project.meta.yaml files for all projects in the repository."""
    repo_root = Path('.')
    
    # Get all directories that look like projects
    projects = []
    for item in repo_root.iterdir():
        if item.is_dir() and not item.name.startswith('.') and item.name != '__monorepo__':
            # Skip some common non-project directories
            if item.name in ['tools', 'Scripts', 'registry', 'nginx']:
                continue
            projects.append(item)
    
    print(f"Found {len(projects)} projects to process")
    
    for project_path in sorted(projects):
        meta_file = project_path / 'project.meta.yaml'
        
        # Skip if already exists
        if meta_file.exists():
            print(f"Skipping {project_path.name} - project.meta.yaml already exists")
            continue
        
        try:
            meta_content = create_project_meta(project_path)
            
            # Write the meta file
            with open(meta_file, 'w') as f:
                yaml.dump(meta_content, f, default_flow_style=False, sort_keys=False)
            
            print(f"Created project.meta.yaml for {project_path.name} ({meta_content.get('language', 'unknown')})")
            
        except Exception as e:
            print(f"Error processing {project_path.name}: {e}")

if __name__ == "__main__":
    main() 