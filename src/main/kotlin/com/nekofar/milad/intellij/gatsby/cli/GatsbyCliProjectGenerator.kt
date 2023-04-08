package com.nekofar.milad.intellij.gatsby.cli

import com.intellij.execution.filters.Filter
import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator
import com.intellij.lang.javascript.boilerplate.NpxPackageDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.vfs.VirtualFile
import com.nekofar.milad.intellij.gatsby.GatsbyBundle
import com.nekofar.milad.intellij.gatsby.GatsbyIcons
import javax.swing.Icon

class GatsbyCliProjectGenerator : NpmPackageProjectGenerator() {
    private data class PackageInfo(val name: String, val executable: String, val command: String)

    private val packageInfo = PackageInfo("gatsby", "gatsby", "new")

    override fun getName(): String = GatsbyBundle.message("gatsby.project.generator.name")

    override fun getDescription(): String = GatsbyBundle.message("gatsby.project.generator.description")

    override fun filters(project: Project, baseDir: VirtualFile): Array<Filter> = emptyArray()

    override fun customizeModule(virtualFile: VirtualFile, contentEntry: ContentEntry?) {}

    override fun packageName(): String = packageInfo.name

    override fun presentablePackageName(): String =
        GatsbyBundle.message("gatsby.project.generator.presentable.package.name")

    override fun getNpxCommands() = listOf(NpxPackageDescriptor.NpxCommand(packageInfo.name, packageInfo.executable))

    override fun generateInTemp(): Boolean = true

    override fun generatorArgs(project: Project?, dir: VirtualFile?, settings: Settings?): Array<String> =
        arrayOf(packageInfo.command, project?.name.toString())

    override fun getIcon(): Icon = GatsbyIcons.ProjectGenerator
}
